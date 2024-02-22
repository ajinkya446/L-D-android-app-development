import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter_tts/flutter_tts.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:http/http.dart' as http;
import 'package:loading_indicator/loading_indicator.dart';
import 'package:speech_to_text/speech_recognition_result.dart';
import 'package:speech_to_text/speech_to_text.dart' as stt;

import 'chat_response_model.dart';

class ChatbotScreen extends StatefulWidget {
  const ChatbotScreen({super.key});

  @override
  State<ChatbotScreen> createState() => _ChatbotScreenState();
}

class _ChatbotScreenState extends State<ChatbotScreen> {
  stt.SpeechToText speech = stt.SpeechToText();
  TextEditingController chatController = TextEditingController();
  ChatResponse? chatResponseList;
  ValueNotifier<List<Map<String, dynamic>>> messageArray = ValueNotifier([]);
  bool isAIOpened = false, isScroll = false, isLoading = false, isMicAvailable = false, listening = false;

  List<String> dropdownvalue = ['English'], tempLang = ['en'];
  List<bool> isTTSEnable = [false];

  FlutterTts flutterTts = FlutterTts();
  var languageList = [
    'English',
    'Hindi',
    'Marathi',
    'Odisha',
    'Bengali',
  ];

  bool get isListening => listening;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    initialiseMic();
  }

  initialiseMic() async {
    await flutterTts.setVolume(100.0);
    isMicAvailable = await speech.initialize();
  }

  @override
  void dispose() {
    super.dispose();
    flutterTts.stop();
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
          resizeToAvoidBottomInset: false,
          backgroundColor: Colors.white,
          appBar: AppBar(
            backgroundColor: const Color(0xff2200FF),
            leading: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8.0),
              child: Image.asset("assets/images/chatbot.png", height: 16),
            ),
            title: Text('Chatbot Support',
                style: GoogleFonts.ptSerif(
                  textStyle: TextStyle(fontSize: 20, fontWeight: FontWeight.w700, color: Colors.white),
                )),
          ),
          body: isLoading
              ? const Center(
                  child: SizedBox(
                  height: 100,
                  width: 100,
                  child: LoadingIndicator(indicatorType: Indicator.ballScaleMultiple, colors: [Color(0xff2200FF), Colors.white], strokeWidth: 2, pathBackgroundColor: Color(0xff2200FF)),
                ))
              : Container(height: double.maxFinite, width: double.maxFinite, child: getChatList()),
          bottomNavigationBar: Padding(
            padding: MediaQuery.of(context).viewInsets,
            child: isLoading
                ? Container(height: 20)
                : Container(
                    height: 70,
                    child: Align(
                      alignment: Alignment.bottomCenter,
                      child: Container(
                        height: 70,
                        padding: const EdgeInsets.symmetric(horizontal: 8),
                        margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 10),
                        decoration: BoxDecoration(borderRadius: BorderRadius.circular(8), border: Border.all(width: 1.5, color: const Color(0xff2200FF))),
                        // margin: const EdgeInsets.symmetric(horizontal: 16),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          mainAxisSize: MainAxisSize.max,
                          children: [
                            // InkWell(onTap: () {}, child: SvgPicture.asset("assets/images/attachment.svg", height: 18, width: 18)),
                            Expanded(
                              child: TextField(
                                  controller: chatController,
                                  onSubmitted: (value) {
                                    // if (kIsWeb) {
                                    isScroll = true;
                                    checkChatResponse();
                                    // }
                                  },
                                  decoration:
                                      const InputDecoration(hintText: 'Your Message', border: InputBorder.none, contentPadding: EdgeInsets.all(16.0), hintStyle: TextStyle(color: Color(0xff2200FF))),
                                  style: const TextStyle(color: Color(0xff2200FF))),
                            ),
                            InkWell(
                              onTap: () async {
                                try {
                                  if (isMicAvailable) {
                                    if (!isListening) {
                                      listening = true;
                                      print("Listening: Microphone");
                                      speech.listen(onResult: _onSpeechResult, listenFor: const Duration(seconds: 20)).then((value) {
                                        Future.delayed(const Duration(seconds: 20), () {
                                          speech.stop();
                                          listening = false;
                                        });
                                      });
                                    } else {
                                      listening = false;
                                      speech.stop();
                                      print("Listening: Microphone  Stopped");
                                    }
                                    setState(() {});
                                    return;
                                  }
                                } catch (e) {
                                  rethrow;
                                }
                              },
                              child: Icon(isListening ? Icons.mic_none_outlined : Icons.mic_off, size: 22.0, color: const Color(0xff2200FF)),
                            ),
                            const SizedBox(width: 20),
                            InkWell(
                              child: const Icon(Icons.send_outlined, size: 22.0, color: Color(0xff2200FF)),
                              onTap: () {
                                isScroll = true;
                                checkChatResponse();
                              },
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
          )),
    );
  }

  /// This is the callback that the SpeechToText plugin calls when
  /// the platform returns recognized words.
  void _onSpeechResult(SpeechRecognitionResult result) {
    setState(() {
      chatController.text = result.recognizedWords;
    });
  }

  Future checkChatResponse() async {
    if (chatController.text.isNotEmpty) {
      if (chatResponseList != null) {
        chatResponseList = null;
      }
      isLoading = true;
      messageArray.value.add({'chatbot': false, 'message': chatController.text});
      dropdownvalue.add("English");
      isTTSEnable.add(false);
      tempLang.add("en");
      setState(() {});
      await collectChatResponse(chatController.text);
      chatController.clear();
      chatController.text = "";
      isLoading = false;
      if (isScroll) {
        SchedulerBinding.instance.addPostFrameCallback((_) {
          scrollController.animateTo(scrollController.position.maxScrollExtent, duration: const Duration(milliseconds: 1), curve: Curves.fastOutSlowIn);
        });
      }
      setState(() {});
    } else {}
  }

  ScrollController scrollController = ScrollController();

  Future collectChatResponse(String question) async {
    try {
      final body = {'language': 'English', 'question': question};
      final details = await http.post(Uri.parse("https://api1.kissangpt.com/v1/inference/text/web"), body: body);
      if (details.statusCode == 200) {
        chatResponseList = ChatResponse.fromJson(jsonDecode(details.body.toString()));
        dropdownvalue.add("English");
        tempLang.add("en");
        isTTSEnable.add(false);
        messageArray.value.add({'chatbot': true, 'message': chatResponseList?.answer ?? ''});
        setState(() {});
      }
    } catch (e) {
      rethrow;
    }
  }

  Widget getChatList() {
    return ValueListenableBuilder(
        valueListenable: messageArray,
        builder: (context, messageItem, child) {
          return messageItem.isNotEmpty
              ? ListView.builder(
                  controller: scrollController,
                  // physics: const ClampingScrollPhysics(),
                  itemCount: messageItem.length,
                  shrinkWrap: true,
                  itemBuilder: (ctx, index) {
                    // GlobalKey newKey = GlobalKey(debugLabel: 'Key$index');
                    return Align(
                      // key: newKey,
                      alignment: messageItem[index]['chatbot'] == true ? Alignment.centerLeft : Alignment.centerRight,
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.end,
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: [
                          Container(
                            margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                            padding: const EdgeInsets.all(8),
                            decoration: BoxDecoration(color: messageItem[index]['chatbot'] == true ? Colors.black12 : Color(0xff2200FF).withOpacity(0.2), borderRadius: BorderRadius.circular(8)),
                            child: Text(messageItem[index]['message'],
                                style: GoogleFonts.ptSerif(
                                  textStyle: TextStyle(fontSize: 14, fontWeight: FontWeight.w400, color: messageItem[index]['chatbot'] == true ? Colors.black : Color(0xff2200FF)),
                                )),
                          ),
                          messageItem[index]['chatbot'] == true
                              ? Container(
                                  margin: const EdgeInsets.symmetric(horizontal: 16),
                                  child: Row(
                                    children: [
                                      GestureDetector(
                                        onTap: () async {
                                          flutterTts.setSpeechRate(0.595);
                                          // await flutterTts.getVoices.then((value) => print("Voices:" + value.toString()));
                                          flutterTts.setVoice({'name': 'Google हिन्दी', 'locale': 'en-US'});
                                          setState(() {});
                                          if (isTTSEnable[index]) {
                                            await flutterTts.stop();
                                            isTTSEnable[index] = false;
                                          } else {
                                            if (tempLang[index] == 'en') {
                                              await flutterTts.speak(messageItem[index]['message']);
                                            } else {
                                              // String valueText = await convertMessage(index, messageItem[index]['message'], tempLang[index], 'hi', isVoiceActive: true);
                                              await flutterTts.speak(messageItem[index]['message']);
                                            }
                                            await flutterTts.setVolume(100);
                                            isTTSEnable[index] = true;
                                          }
                                          setState(() {});
                                          flutterTts.setCompletionHandler(() async {
                                            await flutterTts.stop();
                                            setState(() {
                                              isTTSEnable[index] = false;
                                            });
                                          });
                                        },
                                        child: Icon(
                                          isTTSEnable[index] ? Icons.stop : Icons.headset_mic_sharp,
                                          size: MediaQuery.of(context).size.height * 0.030,
                                          color: const Color(0xff2200FF),
                                        ),
                                      ),
                                      SizedBox(width: MediaQuery.of(context).size.height * 0.020),
                                      DropdownButton(
                                        dropdownColor: Colors.white,
                                        style: GoogleFonts.roboto(
                                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.022, fontWeight: FontWeight.w600, color: const Color(0xff2200FF)),
                                        ),
                                        value: dropdownvalue[index],
                                        icon: Icon(Icons.translate, size: MediaQuery.of(context).size.height * 0.030, color: const Color(0xff2200FF)),
                                        items: languageList.map((String items) {
                                          return DropdownMenuItem(value: items, child: Text(items));
                                        }).toList(),
                                        // After selecting the desired option,it will
                                        // change button value to selected value
                                        onChanged: (String? newValue) async {
                                          setState(() {
                                            isLoading = true;
                                          });
                                          String lang = newValue == 'English'
                                              ? 'en'
                                              : newValue == 'Hindi'
                                                  ? 'hi'
                                                  : newValue == 'Marathi'
                                                      ? 'mr'
                                                      : newValue == 'Odisha'
                                                          ? 'or'
                                                          : newValue == 'Bengali'
                                                              ? 'bn'
                                                              : '';
                                          final messageConverted = await convertMessage(index, messageItem[index]['message'], tempLang[index], lang);
                                          messageArray.value.removeAt(index);
                                          messageArray.value.insert(index, {'chatbot': true, 'message': messageConverted});

                                          setState(() {
                                            dropdownvalue[index] = newValue ?? "";
                                            tempLang[index] = lang;
                                            isLoading = false;
                                          });
                                        },
                                      )
                                    ],
                                  ),
                                )
                              : Container()
                        ],
                      ),
                    );
                  },
                )
              : Container();
        });
  }

  Future convertMessage(int index, String textMessage, String selectedLang, String targetLang, {bool? isVoiceActive = false}) async {
    String message = '';
    try {
      isScroll = false;
      final response = await http.get(Uri.parse("https://translate.googleapis.com/translate_a/single?client=gtx&sl=$selectedLang&tl=$targetLang&dt=t&q=$textMessage"));
      List<dynamic> details = jsonDecode(response.body);
      if (response.statusCode == 200) {
        if (details != null && details.isNotEmpty) {
          // if (!kIsWeb) {
          if (!isVoiceActive!) {
            message = await getDetails(details, isVoiceActive);
          } else {
            message = await getDetails(details, isVoiceActive);
          }
        }
      }
    } catch (e) {
      rethrow;
    }
    return message;
  }

  getDetails(final details, bool isVoiceActive) async {
    String message = '';
    if (details[0].length > 1) {
      String lang = dropdownvalue == 'English' ? 'en' : 'hi';
      await flutterTts.setLanguage(lang);
      for (int i = 0; i < details[0].length; i++) {
        message = "${message + details[0][i][0]}\n";
        message = message.replaceAll('\n\n', '\n').replaceAll('\n\n\n', '\n');
      }
    } else {
      message = details[0].length > 1 ? details[0][1][0] : details[0][0][0];
    }
    return message;
  }
}

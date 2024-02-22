import 'dart:convert';
import 'dart:html' as html;

import 'package:admin/aboutus_screen.dart';
import 'package:admin/chat_response_model.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter_tts/flutter_tts.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:http/http.dart' as http;
import 'package:speech_to_text/speech_recognition_result.dart';
import 'package:speech_to_text/speech_to_text.dart' as stt;

import 'contact_us_screen.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  if (kIsWeb) {
    setUrlStrategy(PathUrlStrategy());
  }
  await Firebase.initializeApp(
      options:
          const FirebaseOptions(apiKey: 'AIzaSyCEwVmxvLGouMqkQxLFHwWtmgL8HtsiXfg', appId: '1:672878074599:web:9a9d2d4544d3a7defc6042', messagingSenderId: '672878074599', projectId: 'firey-chat'));
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      scrollBehavior: MyCustomScrollBehavior(),
      title: 'Admin Kwizee',
      initialRoute: "/",
      routes: {
        '/': (context) => const MyHomePage(),
        '/about': (context) => const AboutScreen(),
        '/contact': (context) => const ContactScreen(),
      },
      debugShowCheckedModeBanner: false,
      theme: ThemeData(primarySwatch: Colors.blue),
      // home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final _scaffoldKey = new GlobalKey<ScaffoldState>();
  stt.SpeechToText speech = stt.SpeechToText();
  TextEditingController chatController = TextEditingController();
  ChatResponse? chatResponseList;
  ValueNotifier<List<Map<String, dynamic>>> messageArray = ValueNotifier([]);
  bool isAIOpened = false, isScroll = false, isMicAvailable = false, listening = false;
  int _counter = 0;
  List<String> dropdownvalue = ['English'], tempLang = ['en'];
  List<bool> isTTSEnable = [false];
  ValueNotifier<bool> isLoading = ValueNotifier(false);

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

  void _incrementCounter() {
    FirebaseFirestore firestore = FirebaseFirestore.instance;
    CollectionReference users = firestore.collection('userdata');
    users.add({'name': "Ajinkya $_counter"}).then((value) => print("User Added")).catchError((error) => print("Failed to add user: $error"));
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      backgroundColor: Colors.white,
      /*appBar: AppBar(
        backgroundColor: Colors.white,
        elevation: 0,
        title: GestureDetector(
          onTap: () => Navigator.pushReplacement(context, MaterialPageRoute(builder: (ctx) => const MyHomePage())),
          child: Text(
            "Kwizee",
            style: GoogleFonts.ptSerif(
              textStyle: const TextStyle(fontSize: 34, color: Color(0xff2200FF), fontWeight: FontWeight.bold, letterSpacing: .5),
            ),
          ),
        ),
        actions: (MediaQuery.of(context).size.width > 500)
            ? [
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextButton(
                      onPressed: () => Navigator.pushReplacementNamed(context, '/about'),
                      child: Text("About Us",
                          style: GoogleFonts.ptSerif(
                            textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.020, fontWeight: FontWeight.w600, color: const Color(0xff2200FF), letterSpacing: .5),
                          ))),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextButton(
                      onPressed: () {},
                      child: Text("Quiz",
                          style: GoogleFonts.ptSerif(
                            textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.020, fontWeight: FontWeight.w600, color: const Color(0xff2200FF), letterSpacing: .5),
                          ))),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextButton(
                      onPressed: () {},
                      child: Text("Contact",
                          style: GoogleFonts.ptSerif(
                            textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.020, fontWeight: FontWeight.w600, color: const Color(0xff2200FF), letterSpacing: .5),
                          ))),
                ),
                Container(
                  alignment: Alignment.center,
                  width: MediaQuery.of(context).size.height * 0.25,
                  margin: EdgeInsets.symmetric(horizontal: MediaQuery.of(context).size.height * 0.036, vertical: MediaQuery.of(context).size.height * 0.012),
                  decoration: BoxDecoration(borderRadius: BorderRadius.circular(22), border: Border.all(color: const Color(0xff2200FF), width: 0.4)),
                  child: Text("Download now",
                      style: GoogleFonts.roboto(
                        textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.020, fontWeight: FontWeight.w400, color: const Color(0xff2200FF)),
                      )),
                )
              ]
            : [],
      ),
      body: Padding(
        padding: EdgeInsets.symmetric(horizontal: MediaQuery.of(context).size.height * 0.016, vertical: 50),
        child: Container(
          height: double.maxFinite,
          child: ListView(
            */ /* mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,*/ /*
            children: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                crossAxisAlignment: CrossAxisAlignment.end,
                children: [
                  Column(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text("Improve your Mind",
                          style: GoogleFonts.sahitya(
                            textStyle: TextStyle(
                                fontSize: (MediaQuery.of(context).size.width > 500) ? MediaQuery.of(context).size.height * 0.06 : MediaQuery.of(context).size.height * 0.03,
                                fontWeight: FontWeight.w700,
                                color: const Color(0xff2200FF)),
                          )),
                      SizedBox(height: MediaQuery.of(context).size.height * 0.015),
                      Container(
                        height: (MediaQuery.of(context).size.width > 500) ? MediaQuery.of(context).size.height * 0.3 : MediaQuery.of(context).size.height * 0.35,
                        width: (MediaQuery.of(context).size.width > 500) ? MediaQuery.of(context).size.width * 0.3 : MediaQuery.of(context).size.height * 0.23,
                        padding: EdgeInsets.symmetric(horizontal: MediaQuery.of(context).size.width * 0.026, vertical: MediaQuery.of(context).size.width * 0.02),
                        alignment: Alignment.center,
                        decoration: const BoxDecoration(
                            color: Color(0xff2200FF), borderRadius: BorderRadius.only(topRight: Radius.circular(22), bottomLeft: Radius.circular(60), bottomRight: Radius.circular(12))),
                        child: Text(
                            "Do you like quizes and competitions?\n"
                            "Find or create quize on any topic!\n"
                            "Play, Share and study in one app",
                            style: GoogleFonts.roboto(
                              textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.022, fontWeight: FontWeight.w400, color: Colors.white),
                            )),
                      )
                    ],
                  ),
                  Image.asset(
                    "assets/images/information.jpg",
                    height: (MediaQuery.of(context).size.width > 500) ? MediaQuery.of(context).size.height * 0.4 : MediaQuery.of(context).size.height * 0.250,
                    width: (MediaQuery.of(context).size.width > 500) ? MediaQuery.of(context).size.height * 0.4 : MediaQuery.of(context).size.height * 0.250,
                  )
                ],
              ),
              const SizedBox(height: 50),
              Padding(
                  padding: EdgeInsets.symmetric(horizontal: MediaQuery.of(context).size.height * 0.016),
                  child: SizedBox(
                    height: MediaQuery.of(context).size.height * 0.31,
                    child: ListView(
                      scrollDirection: Axis.horizontal,
                      */ /* mainAxisAlignment: MainAxisAlignment.spaceAround,
                      crossAxisAlignment: CrossAxisAlignment.end,*/ /*
                      children: [
                        Container(
                          decoration: BoxDecoration(borderRadius: BorderRadius.circular(40), color: const Color(0xffFAFF00).withOpacity(0.4)),
                          margin: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 40 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 20 : 10),
                          padding: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 30 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 10 : 10),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: [
                              Text('Set New categories for quiz',
                                  style: GoogleFonts.roboto(
                                    textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.022, fontWeight: FontWeight.w600, color: const Color(0xff2200FF)),
                                  )),
                              Image.asset("assets/images/jigsaw.png", height: MediaQuery.of(context).size.height * 0.060, width: MediaQuery.of(context).size.height * 0.060),
                              Container(
                                width: MediaQuery.of(context).size.width * 0.11,
                                decoration: BoxDecoration(border: Border.all(color: Colors.white, width: 0.4), borderRadius: BorderRadius.circular(40), color: const Color(0xff2200FF)),
                                margin: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 20 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 10 : 10),
                                padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 8),
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                  children: [
                                    Text('Add More..',
                                        style: GoogleFonts.roboto(
                                          textStyle: TextStyle(
                                              fontSize: (MediaQuery.of(context).size.width > 500) ? MediaQuery.of(context).size.height * 0.022 : MediaQuery.of(context).size.height * 0.018,
                                              fontWeight: FontWeight.w400,
                                              color: Colors.white),
                                        )),
                                    const Icon(Icons.verified, color: Colors.white, size: 20)
                                  ],
                                ),
                              )
                            ],
                          ),
                        ),
                        Container(
                          decoration: BoxDecoration(borderRadius: BorderRadius.circular(40), color: const Color(0xffFAFF00).withOpacity(0.2)),
                          margin: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 40 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 20 : 10),
                          padding: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 30 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 10 : 10),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: [
                              Text('Check all quiz results',
                                  style: GoogleFonts.roboto(
                                    textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.022, fontWeight: FontWeight.w600, color: const Color(0xff2200FF)),
                                  )),
                              Image.asset("assets/images/jigsaw.png", height: MediaQuery.of(context).size.height * 0.060, width: MediaQuery.of(context).size.height * 0.060),
                              Container(
                                width: MediaQuery.of(context).size.width * 0.11,
                                decoration: BoxDecoration(border: Border.all(color: Colors.white, width: 0.4), borderRadius: BorderRadius.circular(40), color: const Color(0xff2200FF)),
                                margin: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 20 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 10 : 10),
                                padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 8),
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                  children: [
                                    Text('Add More..',
                                        style: GoogleFonts.roboto(
                                          textStyle: TextStyle(
                                              fontSize: (MediaQuery.of(context).size.width > 500) ? MediaQuery.of(context).size.height * 0.022 : MediaQuery.of(context).size.height * 0.018,
                                              fontWeight: FontWeight.w400,
                                              color: Colors.white),
                                        )),
                                    const Icon(Icons.verified, color: Colors.white, size: 20)
                                  ],
                                ),
                              )
                            ],
                          ),
                        ),
                        Container(
                          decoration: BoxDecoration(borderRadius: BorderRadius.circular(40), color: const Color(0xffFAFF00).withOpacity(0.2)),
                          margin: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 40 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 20 : 10),
                          padding: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 30 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 10 : 10),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: [
                              Text('Set New Questions for category',
                                  style: GoogleFonts.roboto(
                                    textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.022, fontWeight: FontWeight.w600, color: const Color(0xff2200FF)),
                                  )),
                              Image.asset("assets/images/jigsaw.png", height: MediaQuery.of(context).size.height * 0.060, width: MediaQuery.of(context).size.height * 0.060),
                              Container(
                                width: MediaQuery.of(context).size.width * 0.11,
                                decoration: BoxDecoration(border: Border.all(color: Colors.white, width: 0.4), borderRadius: BorderRadius.circular(40), color: const Color(0xff2200FF)),
                                margin: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 20 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 10 : 10),
                                padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 8),
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                  children: [
                                    Text('Add More..',
                                        style: GoogleFonts.roboto(
                                          textStyle: TextStyle(
                                              fontSize: (MediaQuery.of(context).size.width > 500) ? MediaQuery.of(context).size.height * 0.022 : MediaQuery.of(context).size.height * 0.018,
                                              fontWeight: FontWeight.w400,
                                              color: Colors.white),
                                        )),
                                    const Icon(Icons.verified, color: Colors.white, size: 20)
                                  ],
                                ),
                              )
                            ],
                          ),
                        )
                      ],
                    ),
                  )),
            ],
          ),
        ),
      ),*/
      floatingActionButton: (!isAIOpened)
          ? FloatingActionButton(
              backgroundColor: const Color(0xff2200FF),
              onPressed: () {
                setState(() {
                  isAIOpened = true;
                });
              } /*_incrementCounter*/,
              child: Image.asset("assets/images/chatbot.png", height: 30) /* const Icon(Icons.add)*/,
            )
          : Align(
              alignment: Alignment.bottomRight,
              child: Container(
                margin: const EdgeInsets.only(right: 16),
                height: MediaQuery.of(context).size.height * 0.700,
                width: MediaQuery.of(context).size.width * 0.300,
                child: Card(
                  elevation: 8,
                  color: Colors.grey.shade200,
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    crossAxisAlignment: /*isLoading ? CrossAxisAlignment.center :*/ CrossAxisAlignment.start,
                    children: [
                      Container(
                        padding: const EdgeInsets.symmetric(horizontal: 16),
                        height: MediaQuery.of(context).size.height * 0.10,
                        decoration: const BoxDecoration(
                          // border: Border(bottom: BorderSide(color: Colors.black)),
                          borderRadius: BorderRadius.only(topRight: Radius.circular(24), topLeft: Radius.circular(24)),
                          color: Color(0xff2200FF),
                        ),
                        child: Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
                          Image.asset("assets/images/chatbot.png", height: 30),
                          Text('Chatbot Support',
                              style: GoogleFonts.ptSerif(
                                textStyle: TextStyle(
                                    fontSize: (MediaQuery.of(context).size.width > 500) ? MediaQuery.of(context).size.height * 0.022 : MediaQuery.of(context).size.height * 0.018,
                                    fontWeight: FontWeight.w700,
                                    color: Colors.white),
                              )),
                          IconButton(
                            onPressed: () {
                              setState(() {
                                isAIOpened = false;
                              });
                            },
                            icon: const Icon(Icons.close, color: Colors.white, size: 20),
                          )
                        ]),
                      ),
                      ValueListenableBuilder(
                          valueListenable: isLoading,
                          builder: (newCtx, value, child) {
                            return value
                                ? Container(
                                    child: new ProgressIndicatorDemo(),
                                  )
                                : Container();
                          }),
                      /*isLoading
                          ? const Center(
                              child: SizedBox(
                              height: 100,
                              width: 100,
                              child: LoadingIndicator(indicatorType: Indicator.ballScaleMultiple, colors: [Color(0xff2200FF), Colors.white], strokeWidth: 2, pathBackgroundColor: Color(0xff2200FF)),
                            ))
                          :*/
                      Expanded(child: getChatList()),
                      /*isLoading
                          ? Container()
                          :*/
                      Align(
                        alignment: Alignment.bottomCenter,
                        child: Container(
                          height: 70,
                          padding: const EdgeInsets.symmetric(horizontal: 16),
                          decoration: BoxDecoration(
                              borderRadius: const BorderRadius.only(bottomLeft: Radius.circular(16), bottomRight: Radius.circular(16)), border: Border.all(width: 1.5, color: const Color(0xff2200FF))),
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
                                      if (kIsWeb) {
                                        isScroll = true;
                                        checkChatResponse();
                                      }
                                    },
                                    decoration:
                                        const InputDecoration(hintText: 'Your Message', border: InputBorder.none, contentPadding: EdgeInsets.all(16.0), hintStyle: TextStyle(color: Color(0xff2200FF))),
                                    style: const TextStyle(color: Color(0xff2200FF))),
                              ),
                              InkWell(
                                onTap: () async {
                                  html.window.navigator.getUserMedia(audio: true).then((value) async {
                                    // listening = false;
                                    try {
                                      if (isMicAvailable) {
                                        if (!isListening) {
                                          listening = true;
                                          speech.listen(onResult: _onSpeechResult, listenFor: const Duration(seconds: 20)).then((value) {
                                            Future.delayed(const Duration(seconds: 20), () {
                                              speech.stop();
                                              listening = false;
                                            });
                                          });
                                        } else {
                                          listening = false;
                                          speech.stop();
                                        }
                                        setState(() {});
                                        return;
                                      }
                                    } catch (e) {
                                      rethrow;
                                    }
                                  });
                                  /*if (isMicAvailable) {
                                          if (!isListening) {
                                            listening = true;
                                            print("Enabled: Microphone");
                                            speech.listen(onResult: _onSpeechResult).whenComplete(() => speech.stop());
                                          } else {
                                            listening = false;
                                            speech.stop();
                                            print("Stopped: Microphone  Stopped");
                                          }
                                          setState(() {});
                                          return;
                                        }*/
                                },
                                child: Icon(isListening ? Icons.mic_none_outlined : Icons.mic_off, size: 18.0, color: const Color(0xff2200FF)),
                              ),
                              const SizedBox(width: 20),
                              InkWell(
                                child: const Icon(Icons.send_outlined, size: 18.0, color: Color(0xff2200FF)),
                                onTap: () {
                                  isScroll = true;
                                  checkChatResponse();
                                },
                              ),
                            ],
                          ),
                        ),
                      )
                    ],
                  ),
                ),
              ),
            ), // This trailing comma makes auto-formatting nicer for build methods.
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
      isLoading.value = true;
      messageArray.value.add({'chatbot': false, 'message': chatController.text});
      dropdownvalue.add("English");
      isTTSEnable.add(false);
      tempLang.add("en");
      setState(() {});
      await collectChatResponse(chatController.text);
      chatController.clear();
      chatController.text = "";
      isLoading.value = false;
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
    return ListView.builder(
      controller: scrollController,
      // physics: const ClampingScrollPhysics(),
      itemCount: messageArray.value.length,
      shrinkWrap: true,
      itemBuilder: (ctx, index) {
        // GlobalKey newKey = GlobalKey(debugLabel: 'Key$index');
        return /*isLoading
            ? Container()
            :*/
            Align(
          // key: newKey,
          alignment: messageArray.value[index]['chatbot'] == true ? Alignment.centerLeft : Alignment.centerRight,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.end,
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              Container(
                margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                padding: const EdgeInsets.all(8),
                decoration: BoxDecoration(color: messageArray.value[index]['chatbot'] == true ? Colors.white : Color(0xff2200FF).withOpacity(0.2), borderRadius: BorderRadius.circular(8)),
                child: Text(messageArray.value[index]['message'],
                    style: GoogleFonts.ptSerif(
                      textStyle: TextStyle(fontSize: 14, fontWeight: FontWeight.w400, color: messageArray.value[index]['chatbot'] == true ? Colors.black : Colors.white),
                    )),
              ),
              messageArray.value[index]['chatbot'] == true
                  ? Container(
                      margin: const EdgeInsets.symmetric(horizontal: 16),
                      child: Row(
                        children: [
                          GestureDetector(
                            onTap: () async {
                              flutterTts.setSpeechRate(0.595);
                              await flutterTts.getVoices.then((value) => print("Voices:" + value.toString()));
                              flutterTts.setVoice({'name': 'Google हिन्दी', 'locale': 'en-US'});
                              setState(() {});
                              if (isTTSEnable[index]) {
                                await flutterTts.stop();
                                isTTSEnable[index] = false;
                              } else {
                                if (tempLang[index] == 'en') {
                                  await flutterTts.speak(messageArray.value[index]['message']);
                                } else {
                                  // String valueText = await convertMessage(index, messageItem[index]['message'], tempLang[index], 'hi', isVoiceActive: true);
                                  await flutterTts.speak(messageArray.value[index]['message']);
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
                              // setState(() {
                              isLoading.value = true;
                              // });
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
                              final messageConverted = await convertMessage(index, messageArray.value[index]['message'], tempLang[index], lang);
                              messageArray.value.removeAt(index);
                              messageArray.value.insert(index, {'chatbot': true, 'message': messageConverted});

                              setState(() {
                                dropdownvalue[index] = newValue ?? "";
                                tempLang[index] = lang;
                                isLoading.value = false;
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
    );
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

class MyCustomScrollBehavior extends MaterialScrollBehavior {
  // Override behavior methods and getters like dragDevices
  @override
  Set<PointerDeviceKind> get dragDevices => {
        PointerDeviceKind.touch,
        PointerDeviceKind.mouse,
      };
}

class ProgressIndicatorDemo extends StatefulWidget {
  const ProgressIndicatorDemo({super.key});

  @override
  _ProgressIndicatorDemoState createState() => new _ProgressIndicatorDemoState();
}

class _ProgressIndicatorDemoState extends State<ProgressIndicatorDemo> with SingleTickerProviderStateMixin {
  late AnimationController controller;
  late Animation<double> animation;

  @override
  void initState() {
    super.initState();
    controller = AnimationController(duration: const Duration(milliseconds: 2000), vsync: this);
    animation = Tween(begin: 0.0, end: 1.0).animate(controller)
      ..addListener(() {
        setState(() {
          // the state that has changed here is the animation object’s value
        });
      });
    controller.repeat();
  }

  @override
  void dispose() {
    controller.stop();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Center(
        child: Container(
      child: LinearProgressIndicator(
        value: animation.value,
        color: const Color(0xff2200FF),
      ),
    ));
  }
}

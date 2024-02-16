import 'package:admin/aboutus_screen.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:google_fonts/google_fonts.dart';

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
        '/': (context) => MyHomePage(),
        '/about': (context) => AboutScreen(),
        '/contact': (context) => ContactScreen(),
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
  int _counter = 0;

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
      backgroundColor: Colors.white,
      appBar: AppBar(
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
            /* mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,*/
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
                      /* mainAxisAlignment: MainAxisAlignment.spaceAround,
                      crossAxisAlignment: CrossAxisAlignment.end,*/
                      children: [
                        Container(
                          decoration: BoxDecoration(borderRadius: BorderRadius.circular(40), color: const Color(0xffFAFF00).withOpacity(0.2)),
                          margin: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 40 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 20 : 10),
                          padding: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 30 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 10 : 10),
                          child: Column(
                            children: [
                              SizedBox(height: MediaQuery.of(context).size.height * 0.015),
                              Text('Set New categories for quiz',
                                  style: GoogleFonts.roboto(
                                    textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.022, fontWeight: FontWeight.w600, color: const Color(0xff2200FF)),
                                  )),
                              SizedBox(height: MediaQuery.of(context).size.height * 0.015),
                              Image.asset("assets/images/jigsaw.png", height: MediaQuery.of(context).size.height * 0.080, width: MediaQuery.of(context).size.height * 0.080),
                              SizedBox(height: MediaQuery.of(context).size.height * 0.015),
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
                            children: [
                              SizedBox(height: MediaQuery.of(context).size.height * 0.015),
                              Text('Check all quiz results',
                                  style: GoogleFonts.roboto(
                                    textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.022, fontWeight: FontWeight.w600, color: const Color(0xff2200FF)),
                                  )),
                              SizedBox(height: MediaQuery.of(context).size.height * 0.015),
                              Image.asset("assets/images/jigsaw.png", height: MediaQuery.of(context).size.height * 0.080, width: MediaQuery.of(context).size.height * 0.080),
                              SizedBox(height: MediaQuery.of(context).size.height * 0.015),
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
                            children: [
                              SizedBox(height: MediaQuery.of(context).size.height * 0.015),
                              Text('Set New Questions for category',
                                  style: GoogleFonts.roboto(
                                    textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.022, fontWeight: FontWeight.w600, color: const Color(0xff2200FF)),
                                  )),
                              SizedBox(height: MediaQuery.of(context).size.height * 0.015),
                              Image.asset("assets/images/jigsaw.png", height: MediaQuery.of(context).size.height * 0.080, width: MediaQuery.of(context).size.height * 0.080),
                              SizedBox(height: MediaQuery.of(context).size.height * 0.015),
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
                  ))
            ],
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  List<Widget> getListWidget() {
    return [];
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

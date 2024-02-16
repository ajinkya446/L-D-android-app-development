import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class AboutScreen extends StatefulWidget {
  const AboutScreen({super.key});

  @override
  State<AboutScreen> createState() => _AboutScreenState();
}

class _AboutScreenState extends State<AboutScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        elevation: 0,
        title: GestureDetector(
          onTap: () => Navigator.pushReplacementNamed(context, '/'),
          child: Text(
            "Kwizee",
            style: GoogleFonts.ptSerif(
              textStyle: const TextStyle(fontSize: 34, color: const Color(0xff2200FF), fontWeight: FontWeight.bold, letterSpacing: .5),
            ),
          ),
        ),
        actions: (MediaQuery.of(context).size.width > 500)
            ? [
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextButton(
                      style: TextButton.styleFrom(backgroundColor: const Color(0xff2200FF)),
                      onPressed: () {},
                      child: Text("About Us",
                          style: GoogleFonts.ptSerif(
                            textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.020, fontWeight: FontWeight.w600, color: Colors.white, letterSpacing: .5),
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
                      onPressed: () => Navigator.pushNamed(context, '/contact'),
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
        padding: const EdgeInsets.symmetric(horizontal: 60.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            const SizedBox(height: 60),
            Text("About us",
                style: GoogleFonts.ptSerif(
                  textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.020, fontWeight: FontWeight.w600, color: const Color(0xff2200FF), letterSpacing: .5),
                )),
            const SizedBox(height: 10),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text("We do things differently",
                    style: GoogleFonts.ptSerif(
                      textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.060, fontWeight: FontWeight.w600, color: const Color(0xff000000), letterSpacing: .5),
                    )),
                Text("Learn more about the company, the team \nbehind it, and how we think.",
                    style: GoogleFonts.ptSerif(
                      textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.020, fontWeight: FontWeight.w300, color: const Color(0xff000000), letterSpacing: .5),
                    )),
              ],
            ),
            const SizedBox(height: 30),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text("400+",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.070, fontWeight: FontWeight.w700, color: const Color(0xff2200FF)),
                        )),
                    const SizedBox(height: 5),
                    Text("Quiz's Completed",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.030, fontWeight: FontWeight.w700, color: const Color(0xff000000)),
                        )),
                    const SizedBox(height: 5),
                    Text("We helped to create over 400 amazing quiz test",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.blueGrey),
                        )),
                  ],
                ),
                Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text("600%",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.070, fontWeight: FontWeight.w700, color: const Color(0xff2200FF)),
                        )),
                    const SizedBox(height: 5),
                    Text("Applied for quiz test",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.030, fontWeight: FontWeight.w700, color: const Color(0xff000000)),
                        )),
                    const SizedBox(height: 5),
                    Text("our students have applied on average of 600% \nquiz tests over the period",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.blueGrey),
                        )),
                  ],
                ),
                Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text("0",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.070, fontWeight: FontWeight.w700, color: const Color(0xff2200FF)),
                        )),
                    const SizedBox(height: 5),
                    Text("Global Downloads",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.030, fontWeight: FontWeight.w700, color: const Color(0xff000000)),
                        )),
                    const SizedBox(height: 5),
                    Text("Our free KWIZEE app has been downloaded \nover the globe",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.blueGrey),
                        )),
                  ],
                ),
                Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text("200+",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.070, fontWeight: FontWeight.w700, color: const Color(0xff2200FF)),
                        )),
                    const SizedBox(height: 5),
                    Text("5-Star Reviews",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.030, fontWeight: FontWeight.w700, color: const Color(0xff000000)),
                        )),
                    const SizedBox(height: 5),
                    Text("We have proud of 5 star rating with \nover 200 reviews.",
                        style: GoogleFonts.ptSerif(
                          textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.blueGrey),
                        )),
                  ],
                ),
              ],
            )
          ],
        ),
      ),
      bottomNavigationBar: Container(
        color: Colors.black,
        height: MediaQuery.of(context).size.height * 0.342,
        padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 24),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
                Image.asset("assets/images/logo.png", height: 34),
                Text("Kwizee",
                    style: GoogleFonts.ptSerif(
                      textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.034, fontWeight: FontWeight.w300, color: Colors.white),
                    )),
              ],
            ),
            const SizedBox(height: 10),
            Text("NICE Industrial Area Satpur, MIDC, Nashik, Maharashtra 422007",
                style: GoogleFonts.ptSerif(
                  textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.grey),
                )),
            const SizedBox(height: 10),
            Text("+91 8446619483",
                style: GoogleFonts.ptSerif(
                  textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.grey),
                )),
            const SizedBox(height: 10),
            Text("Ajinkya446@gmail.com",
                style: GoogleFonts.ptSerif(
                  textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.grey),
                )),
            const SizedBox(height: 30),
            Container(height: 2, color: Colors.grey, width: double.maxFinite),
            const SizedBox(height: 16),
            Align(
              alignment: Alignment.bottomCenter,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text("Copyright © 2024 KWIZEE all rights reserved",
                      style: GoogleFonts.ptSerif(
                        textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.grey),
                      )),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      Text("Terms Of Use",
                          style: GoogleFonts.ptSerif(
                            textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.grey),
                          )),
                      const SizedBox(width: 30),
                      Text("Privacy Policy",
                          style: GoogleFonts.ptSerif(
                            textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.grey),
                          )),
                      const SizedBox(width: 30),
                      Text("Cookies Setting",
                          style: GoogleFonts.ptSerif(
                            textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.grey),
                          )),
                      const SizedBox(width: 30),
                    ],
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class ContactScreen extends StatefulWidget {
  const ContactScreen({super.key});

  @override
  State<ContactScreen> createState() => _ContactScreenState();
}

class _ContactScreenState extends State<ContactScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
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
                      onPressed: () => Navigator.pushNamed(context, '/about'),
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
                      style: TextButton.styleFrom(backgroundColor: const Color(0xff2200FF)),
                      onPressed: () => Navigator.pushNamed(context, '/contact'),
                      child: Text("Contact",
                          style: GoogleFonts.ptSerif(
                            textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.020, fontWeight: FontWeight.w600, color: Colors.white, letterSpacing: .5),
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
        padding: const EdgeInsets.symmetric(horizontal: 40.0, vertical: 20),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Column(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text("Contact Us",
                    style: GoogleFonts.roboto(
                      textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.070, fontWeight: FontWeight.w500, color: Colors.black),
                    )),
                const SizedBox(height: 5),
                Text("Email, Call, or complete the form to learn how\n KWIZEE can resolve your problems",
                    style: GoogleFonts.roboto(
                      textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w500, color: Colors.grey),
                    )),
                const SizedBox(height: 12),
                Text("Ajinkya446@gmail.com",
                    style: GoogleFonts.roboto(
                      textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w500, color: Colors.grey),
                    )),
                const SizedBox(height: 12),
                Text("+91 8446619483",
                    style: GoogleFonts.roboto(
                      textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w500, color: Colors.grey),
                    )),
                const SizedBox(height: 50),
                Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text("Feedback and Suggestions",
                            style: GoogleFonts.roboto(
                              textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w700, color: Colors.black),
                            )),
                        const SizedBox(height: 8),
                        Text("We value your feedback and we  \n continuously working to improve\n KWIZEE. Your input is crucial in \nshaping the future of KWIZEE.",
                            style: GoogleFonts.roboto(
                              textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.grey),
                            )),
                      ],
                    ),
                    const SizedBox(width: 30),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text("Media Enquiries",
                            style: GoogleFonts.roboto(
                              textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w700, color: Colors.black),
                            )),
                        const SizedBox(height: 8),
                        Text("For media-related questions or \npress inquiries, please contact us \nat ajinkya446@gmail,com.",
                            style: GoogleFonts.roboto(
                              textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.018, fontWeight: FontWeight.w300, color: Colors.grey),
                            )),
                      ],
                    )
                  ],
                )
              ],
            ),
            Container(
                width: 400,
                child: Card(
                  elevation: 12,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(24),
                  ),
                  child: Container(
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(24),
                      gradient: const LinearGradient(colors: [Color(0xff2200FF), Colors.cyanAccent]),
                    ),
                    child: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 20),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text("Get in Touch",
                              style: GoogleFonts.roboto(
                                textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.042, fontWeight: FontWeight.w700, color: Colors.white),
                              )),
                          const SizedBox(height: 8),
                          Text("You can reach us any time",
                              style: GoogleFonts.roboto(
                                textStyle: TextStyle(fontSize: MediaQuery.of(context).size.height * 0.022, fontWeight: FontWeight.w700, color: Colors.white),
                              )),
                          const SizedBox(height: 8),
                          Container(
                            padding: const EdgeInsets.symmetric(horizontal: 6, vertical: 6),
                            child: TextField(
                              decoration: InputDecoration(
                                hintStyle: TextStyle(color: Colors.white),
                                border: OutlineInputBorder(borderRadius: BorderRadius.circular(16)),
                                hintText: 'Enter your name',
                              ),
                            ),
                          ),
                          const SizedBox(height: 8),
                          Container(
                            padding: const EdgeInsets.symmetric(horizontal: 6, vertical: 6),
                            child: TextField(
                              decoration: InputDecoration(
                                hintStyle: TextStyle(color: Colors.white),
                                border: OutlineInputBorder(borderRadius: BorderRadius.circular(16)),
                                hintText: 'Enter your Contact Number',
                              ),
                            ),
                          ),
                          const SizedBox(height: 8),
                          Container(
                            padding: const EdgeInsets.symmetric(horizontal: 6, vertical: 6),
                            child: TextField(
                              decoration: InputDecoration(
                                hintStyle: TextStyle(color: Colors.white),
                                border: OutlineInputBorder(borderRadius: BorderRadius.circular(16)),
                                hintText: 'Enter your Query',
                              ),
                            ),
                          ),
                          const SizedBox(height: 10),
                          Align(
                            alignment: Alignment.center,
                            child: Container(
                              alignment: Alignment.center,
                              width: MediaQuery.of(context).size.width * 0.11,
                              decoration: BoxDecoration(color: Colors.white, border: Border.all(color: Colors.white, width: 0.4), borderRadius: BorderRadius.circular(40)),
                              margin: EdgeInsets.symmetric(horizontal: (MediaQuery.of(context).size.width > 500) ? 20 : 20, vertical: (MediaQuery.of(context).size.width > 500) ? 10 : 10),
                              padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 8),
                              child: Text('Submit',
                                  style: GoogleFonts.roboto(
                                    textStyle: TextStyle(
                                        fontSize: (MediaQuery.of(context).size.width > 500) ? MediaQuery.of(context).size.height * 0.022 : MediaQuery.of(context).size.height * 0.018,
                                        fontWeight: FontWeight.w700,
                                        color: const Color(0xff2200FF)),
                                  )),
                            ),
                          )
                        ],
                      ),
                    ),
                  ),
                ))
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

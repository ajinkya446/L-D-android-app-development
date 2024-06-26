import 'dart:ui';

import 'package:flutter/material.dart';

late FragmentProgram fragmentProgram;

void main() async {
  fragmentProgram = await FragmentProgram.fromAsset("assets/my_shader.frag");
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: CustomPaint(painter: MyPainter(Colors.green, shader: fragmentProgram.fragmentShader())),
    );
  }
}

class MyPainter extends CustomPainter {
  final Color color;
  final FragmentShader shader;

  MyPainter(this.color, {required this.shader});

  @override
  void paint(Canvas canvas, Size size) {
    // TODO: implement paint
    shader.setFloat(0, size.width);
    shader.setFloat(1, size.height);
    shader.setFloat(2, color.red.toDouble() / 255);
    shader.setFloat(3, color.green.toDouble() / 255);
    shader.setFloat(4, color.blue.toDouble() / 255);
    shader.setFloat(5, color.alpha.toDouble() / 255);
    canvas.drawRect(Rect.fromLTWH(0, 0, size.width, size.height), Paint()..shader = shader);
  }

  @override
  bool shouldRepaint(MyPainter oldDelegate) => color != oldDelegate.color;
}

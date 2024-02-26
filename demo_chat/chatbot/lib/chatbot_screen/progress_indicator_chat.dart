import 'package:flutter/material.dart';

class ProgressIndicatorChat extends StatefulWidget {
  const ProgressIndicatorChat({super.key});

  @override
  ProgressIndicatorChatState createState() => ProgressIndicatorChatState();
}

class ProgressIndicatorChatState extends State<ProgressIndicatorChat> with SingleTickerProviderStateMixin {
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
      child: LinearProgressIndicator(value: animation.value, color: const Color(0xff2200FF)),
    ));
  }
}

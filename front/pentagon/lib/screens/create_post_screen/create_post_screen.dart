import 'package:flutter/material.dart';

class CreatePostScreen extends StatelessWidget {
  final bool shadow;

  const CreatePostScreen({
    this.shadow = false,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(10),
      child: AnimatedContainer(
        curve: Curves.fastLinearToSlowEaseIn,
        duration: const Duration(seconds: 3),
        decoration: BoxDecoration(
          boxShadow: shadow
              ? [
                  const BoxShadow(
                    blurRadius: 60,
                    spreadRadius: 20,
                  ),
                ]
              : null,
          color: Theme.of(context).canvasColor,
          borderRadius: BorderRadius.circular(20),
        ),
        child: const Text('a'),
      ),
    );
  }
}

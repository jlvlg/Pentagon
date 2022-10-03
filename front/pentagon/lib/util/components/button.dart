import 'package:flutter/material.dart';

class Button extends StatelessWidget {
  final Color? color;
  final Color? textColor;
  final Function() onPressed;
  final String label;

  const Button({
    required this.label,
    required this.onPressed,
    this.color,
    this.textColor,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: onPressed,
      style: ElevatedButton.styleFrom(
        backgroundColor: color ?? Theme.of(context).colorScheme.secondary,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
        foregroundColor: textColor ?? Colors.white,
      ),
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 10),
        child: Text(
          label,
          style: const TextStyle(fontSize: 20),
        ),
      ),
    );
  }
}

import 'package:flutter/material.dart';
import 'package:pentagon/util/constants/app_colors.dart';

class TextInput extends StatelessWidget {
  final String label;
  final void Function(String)? onChanged;
  final TextInputAction? textInputAction;
  final String? Function(String?)? validator;
  final String? initialText;
  final void Function(String?)? onSaved;
  final TextEditingController? controller;
  final void Function(String)? onSubmitted;
  final int maxLines;
  final int? maxLength;
  final bool obscureText;
  final EdgeInsets margin;

  const TextInput({
    required this.label,
    this.validator,
    this.onSaved,
    this.onChanged,
    this.initialText,
    this.textInputAction,
    this.controller,
    this.maxLines = 1,
    this.maxLength,
    this.onSubmitted,
    this.obscureText = false,
    this.margin = const EdgeInsets.all(5),
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: margin,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            padding: const EdgeInsets.only(left: 5, bottom: 5),
            child: Text(
              label,
              style: const TextStyle(fontSize: 15),
            ),
          ),
          TextFormField(
            autovalidateMode: AutovalidateMode.onUserInteraction,
            maxLines: maxLines,
            maxLength: maxLength,
            onChanged: onChanged,
            decoration: InputDecoration(
              filled: true,
              fillColor: const Color(0xFFEAEAEA),
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(10),
              ),
              enabledBorder: OutlineInputBorder(
                borderRadius: BorderRadius.circular(10),
                borderSide: const BorderSide(
                  color: AppColors.inputBorderColor,
                ),
              ),
            ),
            validator: validator,
            onSaved: onSaved,
            textInputAction: textInputAction,
            controller: controller ?? TextEditingController(text: initialText),
            obscureText: obscureText,
            onFieldSubmitted: onSubmitted,
          ),
        ],
      ),
    );
  }
}

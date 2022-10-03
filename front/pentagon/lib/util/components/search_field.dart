import 'package:flutter/material.dart';
import 'package:pentagon/util/constants/app_colors.dart';
import 'package:pentagon/util/constants/app_routes.dart';

class SearchField extends StatelessWidget {
  final String? initialText;
  final Function(String)? onSubmitted;

  const SearchField({this.initialText, this.onSubmitted, super.key});

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: TextEditingController(text: initialText),
      onSubmitted: onSubmitted ??
          (text) => Navigator.of(context)
              .pushNamed(AppRoutes.search, arguments: text),
      decoration: InputDecoration(
        suffixIcon: const Icon(Icons.search),
        constraints: const BoxConstraints(maxHeight: 40),
        contentPadding: const EdgeInsets.only(left: 10),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(90),
        ),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(90),
          borderSide: const BorderSide(color: AppColors.inputBorderColor),
        ),
      ),
    );
  }
}

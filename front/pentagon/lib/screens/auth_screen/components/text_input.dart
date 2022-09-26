import 'package:flutter/material.dart';

class TextInput extends StatelessWidget {
  final String label;
  final String? Function(String?)? validator;

  const TextInput({required this.label, this.validator, super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
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
          decoration: InputDecoration(
            filled: true,
            fillColor: const Color(0xFFEAEAEA),
            border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(10),
            ),
            enabledBorder: OutlineInputBorder(
              borderRadius: BorderRadius.circular(10),
              borderSide: const BorderSide(
                color: Color(0xFF989898),
              ),
            ),
          ),
          validator: validator,
        ),
      ],
    );
  }
}

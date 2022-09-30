import 'package:flutter/material.dart';
import 'package:pentagon/util/components/search_field.dart';

class FeedScreen extends StatelessWidget {
  const FeedScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(5),
      child: Column(
        children: const [SearchField()],
      ),
    );
  }
}

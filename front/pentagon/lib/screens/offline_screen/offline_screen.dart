import 'package:flutter/material.dart';
import 'package:pentagon/generated/l10n.dart';

class OfflineScreen extends StatelessWidget {
  const OfflineScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Icon(Icons.wifi_off),
            Text(S.of(context).offline),
          ],
        ),
      ),
    );
  }
}

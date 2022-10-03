import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/material.dart';
import 'package:pentagon/screens/offline_screen/offline_screen.dart';
import 'package:provider/provider.dart';

class EnsureOnline extends StatelessWidget {
  final Widget child;

  const EnsureOnline(this.child, {super.key});

  @override
  Widget build(BuildContext context) {
    return context.watch<ConnectivityResult>() != ConnectivityResult.none
        ? child
        : const OfflineScreen();
  }
}

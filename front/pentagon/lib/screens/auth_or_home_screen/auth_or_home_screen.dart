import 'package:flutter/material.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/screens/auth_screen/auth_screen.dart';
import 'package:pentagon/screens/home_screen/home_screen.dart';
import 'package:provider/provider.dart';

class AuthOrHomeScreen extends StatelessWidget {
  final int initScreen;

  const AuthOrHomeScreen({this.initScreen = 0, super.key});

  @override
  Widget build(BuildContext context) {
    return Provider.of<AuthProvider>(context).isAuth
        ? HomeScreen(initScreen: initScreen)
        : const AuthScreen();
  }
}

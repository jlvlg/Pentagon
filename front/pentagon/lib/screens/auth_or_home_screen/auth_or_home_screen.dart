import 'package:flutter/material.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/screens/auth_screen/auth_screen.dart';
import 'package:pentagon/screens/home_screen/home_screen.dart';
import 'package:pentagon/util/components/ensure_online.dart';
import 'package:provider/provider.dart';

class AuthOrHomeScreen extends StatelessWidget {
  final int initScreen;

  const AuthOrHomeScreen({this.initScreen = 0, super.key});

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;

    return Stack(
      children: [
        context.watch<AuthProvider>().isAuth
            ? EnsureOnline(HomeScreen(initScreen: initScreen))
            : const AuthScreen(),
        Positioned(
          height: size.height,
          width: size.width,
          top: -size.height,
          child: Hero(
            tag: 'splash_bg',
            child: FittedBox(
              fit: BoxFit.fill,
              child: Image.asset(
                'assets/images/splash-bg.png',
              ),
            ),
          ),
        ),
      ],
    );
  }
}

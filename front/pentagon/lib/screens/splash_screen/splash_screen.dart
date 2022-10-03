import 'dart:async';

import 'package:flutter/material.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/util/constants/app_colors.dart';
import 'package:pentagon/util/constants/app_routes.dart';
import 'package:provider/provider.dart';

class SplashScreen extends StatelessWidget {
  const SplashScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    Timer(const Duration(seconds: 2), () {
      context.read<AuthProvider>().tryAutoLogin().whenComplete(() {
        Navigator.of(context).pushReplacementNamed(AppRoutes.home);
      });
    });
    return Stack(
      children: [
        Container(
          height: double.infinity,
          width: double.infinity,
          color: AppColors.primary,
          child: Hero(
            tag: 'splash_bg',
            child: FittedBox(
              fit: BoxFit.fill,
              child: Image.asset('assets/images/splash-bg.png'),
            ),
          ),
        ),
        Center(
          child: Hero(
            tag: 'icon',
            child: Image.asset(
              'assets/images/icons/icon-512x512.png',
              height: size.shortestSide * .5,
              width: size.shortestSide * .5,
            ),
          ),
        ),
      ],
    );
  }
}

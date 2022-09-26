
import 'package:flutter/material.dart';
import 'package:pentagon/util/constants/app_colors.dart';

class SplashScreen extends StatelessWidget {
  const SplashScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    // Timer(const Duration(seconds: 3),
    //     () => Navigator.of(context).pushReplacementNamed(AppRoutes.auth));
    return Stack(
      children: [
        Container(
          height: double.infinity,
          width: double.infinity,
          color: AppColors.primary,
          child: FittedBox(
            fit: BoxFit.fill,
            child: Hero(
                tag: 'splash_bg',
                child: Image.asset('assets/images/splash-bg.png')),
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

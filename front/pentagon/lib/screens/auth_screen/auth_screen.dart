import 'package:flutter/material.dart';
import 'package:pentagon/screens/auth_screen/components/auth_form.dart';
import 'package:pentagon/util/constants/app_colors.dart';

class AuthScreen extends StatelessWidget {
  const AuthScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;

    return Scaffold(
      backgroundColor: AppColors.primary,
      body: Stack(
        children: [
          const Center(
            child: AuthForm(),
          ),
          Positioned(
            height: size.height,
            width: size.width,
            top: -size.height,
            child: FittedBox(
              fit: BoxFit.fill,
              child: Hero(
                  tag: 'splash_bg',
                  child: Image.asset('assets/images/splash-bg.png')),
            ),
          ),
        ],
      ),
    );
  }
}

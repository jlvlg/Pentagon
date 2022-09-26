import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/screens/auth_screen/auth_screen.dart';
import 'package:pentagon/screens/tab_screen/tab_screen.dart';
import 'package:pentagon/screens/splash_screen/splash_screen.dart';
import 'package:pentagon/util/constants/app_colors.dart';
import 'package:pentagon/util/constants/app_names.dart';
import 'package:pentagon/util/constants/app_routes.dart';

class PentagonApp extends StatelessWidget {
  final String _locale;

  const PentagonApp(this._locale, {super.key});

  @override
  Widget build(BuildContext context) {
    final locale = _locale.split('_');
    return MaterialApp(
      localizationsDelegates: const [
        S.delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
      ],
      supportedLocales: S.delegate.supportedLocales,
      locale:
          locale.length == 1 ? Locale(locale[0]) : Locale(locale[0], locale[1]),
      title: AppNames.appTitle,
      theme: ThemeData.light().copyWith(
        colorScheme: const ColorScheme.light().copyWith(
          primary: AppColors.primary,
          secondary: AppColors.secondary,
        ),
      ),
      home: const TabScreen(),
      // routes: {
      //   AppRoutes.splash: (context) => const SplashScreen(),
      //   AppRoutes.home: (context) => const HomeScreen(),
      //   AppRoutes.auth: (context) => const AuthScreen(),
      // },
      onGenerateRoute: (settings) {
        const routes = {
          AppRoutes.splash: SplashScreen(),
          AppRoutes.home: TabScreen(),
          AppRoutes.auth: AuthScreen(),
        };
        if (routes.containsKey(settings.name)) {
          return PageRouteBuilder(
              settings: settings,
              pageBuilder: (_, __, ___) => routes[settings.name]!);
        }
        return null;
      },
    );
  }
}

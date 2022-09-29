import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/providers/localizations_provider.dart';
import 'package:pentagon/screens/auth_or_home_screen/auth_or_home_screen.dart';
import 'package:pentagon/screens/splash_screen/splash_screen.dart';
import 'package:pentagon/util/constants/app_colors.dart';
import 'package:pentagon/util/constants/app_names.dart';
import 'package:pentagon/util/constants/app_routes.dart';
import 'package:provider/provider.dart';

class PentagonApp extends StatelessWidget {
  const PentagonApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => AuthProvider()),
      ],
      child: MaterialApp(
        localizationsDelegates: const [
          S.delegate,
          GlobalMaterialLocalizations.delegate,
          GlobalCupertinoLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate,
        ],
        debugShowCheckedModeBanner: false,
        supportedLocales: S.delegate.supportedLocales,
        locale: Provider.of<LocalizationsProvider>(context).locale,
        title: AppNames.appTitle,
        theme: ThemeData.light().copyWith(
          colorScheme: const ColorScheme.light().copyWith(
            primary: AppColors.primary,
            secondary: AppColors.secondary,
          ),
        ),
        // routes: {
        //   AppRoutes.splash: (context) => const SplashScreen(),
        //   AppRoutes.home: (context) => const HomeScreen(),
        //   AppRoutes.auth: (context) => const AuthScreen(),
        // },
        onGenerateRoute: (settings) {
          const routes = {
            AppRoutes.splash: SplashScreen(),
            AppRoutes.home: AuthOrHomeScreen(),
          };
          if (routes.containsKey(settings.name)) {
            return PageRouteBuilder(
                settings: settings,
                transitionDuration: Duration(seconds: 1),
                pageBuilder: (_, __, ___) => routes[settings.name]!);
          }
          return null;
        },
      ),
    );
  }
}

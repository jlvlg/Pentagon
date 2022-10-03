import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/providers/locale_provider.dart';
import 'package:pentagon/providers/post_provider.dart';
import 'package:pentagon/providers/profile_provider.dart';
import 'package:pentagon/screens/auth_or_home_screen/auth_or_home_screen.dart';
import 'package:pentagon/screens/profile_screen/profile_screen.dart';
import 'package:pentagon/screens/search_screen.dart/search_screen.dart';
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
        StreamProvider(
          create: (_) => Connectivity().onConnectivityChanged,
          initialData: ConnectivityResult.none,
        ),
        ChangeNotifierProvider(create: (_) => AuthProvider()),
        ChangeNotifierProxyProvider<AuthProvider, ProfileProvider>(
          create: (_) => ProfileProvider(),
          update: (context, value, previous) => ProfileProvider(
            value.token ?? '',
            previous?.profile,
            previous?.profiles ?? [],
            value.authProfile,
          ),
        ),
        ChangeNotifierProxyProvider<AuthProvider, PostProvider>(
          create: (_) => PostProvider(),
          update: (context, value, previous) => PostProvider(
            value.token ?? '',
            previous?.post,
            value.authProfile,
            previous?.posts ?? [],
          ),
        ),
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
        locale: Provider.of<LocaleProvider>(context).locale,
        title: AppNames.appTitle,
        theme: ThemeData.light().copyWith(
          colorScheme: const ColorScheme.light().copyWith(
            primary: AppColors.primary,
            secondary: AppColors.secondary,
          ),
        ),
        onGenerateRoute: (settings) {
          Widget? route;
          switch (settings.name) {
            case AppRoutes.splash:
              route = const SplashScreen();
              break;
            case AppRoutes.home:
              route = AuthOrHomeScreen(
                  initScreen: (settings.arguments ?? 0) as int);
              break;
            case AppRoutes.search:
              route = SearchScreen(settings.arguments as String);
              break;
            case AppRoutes.profile:
              route = ProfileScreen(settings.arguments as Profile);
          }
          return PageRouteBuilder(
            settings: settings,
            transitionDuration: const Duration(seconds: 1),
            pageBuilder: (_, __, ___) => route!,
          );
        },
      ),
    );
  }
}

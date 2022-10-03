import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:pentagon/app.dart';
import 'package:pentagon/data/store.dart';
import 'package:pentagon/providers/locale_provider.dart';
import 'package:provider/provider.dart';
import 'package:universal_io/io.dart';
import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  final settings = await Store.getStrings(
    ['locale'],
    [Platform.localeName],
  );

  final locale = settings['locale']!.split('_');

  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );

  if (MediaQueryData.fromWindow(WidgetsBinding.instance.window)
          .size
          .shortestSide <
      550) {
    await SystemChrome.setPreferredOrientations([DeviceOrientation.portraitUp]);
  }

  runApp(
    ChangeNotifierProvider(
      create: (context) => LocaleProvider(
        Locale(locale[0], locale.length == 2 ? locale[1] : null),
      ),
      child: const PentagonApp(),
    ),
  );
}

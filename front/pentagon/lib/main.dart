import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_phoenix/flutter_phoenix.dart';
import 'package:pentagon/app.dart';
import 'package:pentagon/data/store.dart';
import 'package:pentagon/providers/localizations_provider.dart';
import 'package:provider/provider.dart';
import 'package:universal_io/io.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

  final data = MediaQueryData.fromWindow(WidgetsBinding.instance.window);
  if (data.size.shortestSide < 600) {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);
  }

  Store.getStrings(
    ['locale'],
    [Platform.localeName],
  ).then(
    (settings) {
      final locale = settings['locale']!.split('_');
      runApp(
        ChangeNotifierProvider(
          create: (context) => LocalizationsProvider(
            Locale(locale[0], locale.length == 2 ? locale[1] : null),
          ),
          child: PentagonApp(),
        ),
      );
    },
  );
}

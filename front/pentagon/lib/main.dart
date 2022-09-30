import 'package:flutter/material.dart';
import 'package:pentagon/app.dart';
import 'package:pentagon/data/store.dart';
import 'package:pentagon/providers/localizations_provider.dart';
import 'package:provider/provider.dart';
import 'package:universal_io/io.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

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
          child: const PentagonApp(),
        ),
      );
    },
  );
}

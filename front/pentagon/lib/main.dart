import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_phoenix/flutter_phoenix.dart';
import 'package:pentagon/app.dart';
import 'package:pentagon/data/store.dart';

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
      runApp(
        Phoenix(
          child: PentagonApp(
            settings['locale']!,
          ),
        ),
      );
    },
  );
}

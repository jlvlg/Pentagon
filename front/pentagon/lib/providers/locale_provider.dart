import 'package:flutter/material.dart';
import 'package:pentagon/data/store.dart';

class LocaleProvider with ChangeNotifier {
  Locale locale;

  LocaleProvider(this.locale);

  set setLocale(String locale) {
    this.locale = Locale(locale);
    Store.saveString('locale', locale);
    notifyListeners();
  }
}

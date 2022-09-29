import 'package:flutter/material.dart';

class LocalizationsProvider with ChangeNotifier {
  Locale _locale;

  LocalizationsProvider(this._locale);

  set setLocale(String locale) {
    _locale = Locale(locale);
    notifyListeners();
  }

  get locale => _locale;
}

// GENERATED CODE - DO NOT MODIFY BY HAND
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'intl/messages_all.dart';

// **************************************************************************
// Generator: Flutter Intl IDE plugin
// Made by Localizely
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, lines_longer_than_80_chars
// ignore_for_file: join_return_with_assignment, prefer_final_in_for_each
// ignore_for_file: avoid_redundant_argument_values, avoid_escaping_inner_quotes

class S {
  S();

  static S? _current;

  static S get current {
    assert(_current != null,
        'No instance of S was loaded. Try to initialize the S delegate before accessing S.current.');
    return _current!;
  }

  static const AppLocalizationDelegate delegate = AppLocalizationDelegate();

  static Future<S> load(Locale locale) {
    final name = (locale.countryCode?.isEmpty ?? false)
        ? locale.languageCode
        : locale.toString();
    final localeName = Intl.canonicalizedLocale(name);
    return initializeMessages(localeName).then((_) {
      Intl.defaultLocale = localeName;
      final instance = S();
      S._current = instance;

      return instance;
    });
  }

  static S of(BuildContext context) {
    final instance = S.maybeOf(context);
    assert(instance != null,
        'No instance of S present in the widget tree. Did you add S.delegate in localizationsDelegates?');
    return instance!;
  }

  static S? maybeOf(BuildContext context) {
    return Localizations.of<S>(context, S);
  }

  /// `Username`
  String get username {
    return Intl.message(
      'Username',
      name: 'username',
      desc: '',
      args: [],
    );
  }

  /// `Password`
  String get password {
    return Intl.message(
      'Password',
      name: 'password',
      desc: '',
      args: [],
    );
  }

  /// `Login`
  String get login {
    return Intl.message(
      'Login',
      name: 'login',
      desc: '',
      args: [],
    );
  }

  /// `Sign Up`
  String get signup {
    return Intl.message(
      'Sign Up',
      name: 'signup',
      desc: '',
      args: [],
    );
  }

  /// `Confirm password`
  String get confirmPassword {
    return Intl.message(
      'Confirm password',
      name: 'confirmPassword',
      desc: '',
      args: [],
    );
  }

  /// `Authentication failure, your username or password might be incorrect`
  String get authExceptionMsg {
    return Intl.message(
      'Authentication failure, your username or password might be incorrect',
      name: 'authExceptionMsg',
      desc: '',
      args: [],
    );
  }

  /// `{field} is a required field`
  String emptyFieldValidation(Object field) {
    return Intl.message(
      '$field is a required field',
      name: 'emptyFieldValidation',
      desc: '',
      args: [field],
    );
  }

  /// `Field contains invalid characters`
  String get invalidFieldValidation {
    return Intl.message(
      'Field contains invalid characters',
      name: 'invalidFieldValidation',
      desc: '',
      args: [],
    );
  }

  /// `Passwords do not match`
  String get passwordsDoNotMatch {
    return Intl.message(
      'Passwords do not match',
      name: 'passwordsDoNotMatch',
      desc: '',
      args: [],
    );
  }

  /// `{error} length is {length}`
  String passwordInvalidLength(Object error, Object length) {
    return Intl.message(
      '$error length is $length',
      name: 'passwordInvalidLength',
      desc: '',
      args: [error, length],
    );
  }

  /// `Minimum`
  String get minimum {
    return Intl.message(
      'Minimum',
      name: 'minimum',
      desc: '',
      args: [],
    );
  }

  /// `Maximum`
  String get maximum {
    return Intl.message(
      'Maximum',
      name: 'maximum',
      desc: '',
      args: [],
    );
  }

  /// `An {error} error has ocurred`
  String onError(Object error) {
    return Intl.message(
      'An $error error has ocurred',
      name: 'onError',
      desc: '',
      args: [error],
    );
  }

  /// `Username taken`
  String get usernameTakenException {
    return Intl.message(
      'Username taken',
      name: 'usernameTakenException',
      desc: '',
      args: [],
    );
  }

  /// `Invalid {field}`
  String invalidFieldException(Object field) {
    return Intl.message(
      'Invalid $field',
      name: 'invalidFieldException',
      desc: '',
      args: [field],
    );
  }

  /// `unexpected`
  String get unexpected {
    return Intl.message(
      'unexpected',
      name: 'unexpected',
      desc: '',
      args: [],
    );
  }

  /// `Home`
  String get home {
    return Intl.message(
      'Home',
      name: 'home',
      desc: '',
      args: [],
    );
  }

  /// `Profile`
  String get profile {
    return Intl.message(
      'Profile',
      name: 'profile',
      desc: '',
      args: [],
    );
  }

  /// `Settings`
  String get settings {
    return Intl.message(
      'Settings',
      name: 'settings',
      desc: '',
      args: [],
    );
  }

  /// `Bad request`
  String get badRequest {
    return Intl.message(
      'Bad request',
      name: 'badRequest',
      desc: '',
      args: [],
    );
  }

  /// `{object} not found`
  String objectNotFound(Object object) {
    return Intl.message(
      '$object not found',
      name: 'objectNotFound',
      desc: '',
      args: [object],
    );
  }

  /// `No {object} found`
  String noObjectfound(Object object) {
    return Intl.message(
      'No $object found',
      name: 'noObjectfound',
      desc: '',
      args: [object],
    );
  }

  /// `User`
  String get user {
    return Intl.message(
      'User',
      name: 'user',
      desc: '',
      args: [],
    );
  }

  /// `Access forbidden`
  String get accessForbidden {
    return Intl.message(
      'Access forbidden',
      name: 'accessForbidden',
      desc: '',
      args: [],
    );
  }

  /// `Logout`
  String get logout {
    return Intl.message(
      'Logout',
      name: 'logout',
      desc: '',
      args: [],
    );
  }
}

class AppLocalizationDelegate extends LocalizationsDelegate<S> {
  const AppLocalizationDelegate();

  List<Locale> get supportedLocales {
    return const <Locale>[
      Locale.fromSubtags(languageCode: 'en'),
      Locale.fromSubtags(languageCode: 'pt'),
    ];
  }

  @override
  bool isSupported(Locale locale) => _isSupported(locale);
  @override
  Future<S> load(Locale locale) => S.load(locale);
  @override
  bool shouldReload(AppLocalizationDelegate old) => false;

  bool _isSupported(Locale locale) {
    for (var supportedLocale in supportedLocales) {
      if (supportedLocale.languageCode == locale.languageCode) {
        return true;
      }
    }
    return false;
  }
}

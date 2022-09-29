import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:pentagon/data/store.dart';
import 'package:pentagon/exceptions/auth_exception.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/models/auth.dart';
import 'package:pentagon/util/constants/api_endpoints.dart';
import 'package:pentagon/util/helpers/api.dart';

class AuthProvider with ChangeNotifier {
  Auth? auth;

  bool get isAuth => auth != null;

  Future<void> _authenticate(
      String username, String password, String urlFragment) async {
    final response = await Api.post(
      '${ApiEndpoints.auth}/$urlFragment',
      data: {
        'username': username,
        'password': password,
      },
    );

    switch (response.statusCode) {
      case 403:
        throw AuthException(S.current.authExceptionMsg);
      case 409:
        throw AuthException(S.current.usernameTakenException);
      case 422:
        throw AuthException(
            S.current.invalidFieldException(S.current.username));
    }

    final body = jsonDecode(response.body);

    auth = Auth(
      token: response.headers['authorization']!,
      id: body['id'].toString(),
      username: body['auth']['username'],
    );

    Store.saveMap(
      'userDetails',
      auth!.toMap,
    );

    notifyListeners();
  }

  Future<void> login(String username, String password) async =>
      _authenticate(username, password, 'login');

  Future<void> signUp(String username, String password) async =>
      _authenticate(username, password, 'signup');

  Future<void> tryAutoLogin() async {
    if (!isAuth) {
      final userData = await Store.getMap('userDetails');
      if (userData.isNotEmpty) {
        auth = Auth(
          id: userData['id'],
          token: userData['token'],
          username: userData['username'],
        );
      }
    }
  }

  void logout() {
    auth = null;
    Store.remove('userDetails').whenComplete(() {
      notifyListeners();
    });
  }
}

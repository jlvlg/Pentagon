
import 'package:flutter/material.dart';
import 'package:pentagon/data/store.dart';
import 'package:pentagon/models/user.dart';
import 'package:pentagon/controllers/auth_controller.dart';

class AuthProvider with ChangeNotifier {
  User? _authUser;

  bool get isAuth => _authUser != null && _authUser!.auth.token != null;
  User? get authUser => isAuth ? _authUser : null;
  String? get token => authUser?.auth.token;
  String? get id => authUser?.id;

  Future<void> _authenticate(
      String username, String password, String urlFragment) async {
    _authUser =
        await AuthController.authenticate(username, password, urlFragment);

    Store.saveMap(
      'userDetails',
      _authUser!.toMap,
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
        _authUser = User.fromMap(userData, userData['auth']['token']);
      }
    }
  }

  void logout() {
    _authUser = null;
    Store.remove('userDetails').whenComplete(() {
      notifyListeners();
    });
  }
}

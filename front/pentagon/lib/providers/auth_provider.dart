import 'dart:io';

import 'package:flutter/material.dart';
import 'package:pentagon/controllers/profile_controller.dart';
import 'package:pentagon/data/store.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/models/user.dart';
import 'package:pentagon/controllers/auth_controller.dart';

class AuthProvider with ChangeNotifier {
  Profile? _authProfile;

  bool get isAuth =>
      _authProfile != null && _authProfile!.user.auth.token != null;
  Profile? get authProfile => isAuth ? _authProfile : null;
  User? get authUser => authProfile?.user;
  String? get id => authProfile?.id;
  String? get token => authUser?.auth.token;
  String? get uid => authUser?.id;

  Future<void> _authenticate(
      String username, String password, String urlFragment) async {
    _authProfile =
        await AuthController.authenticate(username, password, urlFragment);

    Store.saveMap(
      'userDetails',
      _authProfile!.toMap,
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
        _authProfile =
            Profile.fromMap(userData, userData['user']['auth']['token']);
      }
    }
  }

  Future<void> deleteAccount() async {
    if (isAuth) {
      await ProfileController.deleteProfile(
        authProfile!,
        token: token!,
      );
      logout();
    }
  }

  Future<void> reloadProfile() async {
    if (isAuth) {
      _authProfile = await ProfileController.getProfile(
        id: id,
        token: token!,
        setToken: true,
      );
      Store.saveMap(
        'userDetails',
        _authProfile!.toMap,
      );
      notifyListeners();
    }
  }

  Future<void> patchProfile(Profile profile, File? image) async {
    if (isAuth) {
      _authProfile = await ProfileController.patchProfile(
        profile,
        image: image,
        token: token!,
        setToken: true,
      );
      Store.saveMap(
        'userDetails',
        _authProfile!.toMap,
      );
      notifyListeners();
    }
  }

  void logout() {
    _authProfile = null;
    Store.remove('userDetails').whenComplete(() {
      notifyListeners();
    });
  }
}

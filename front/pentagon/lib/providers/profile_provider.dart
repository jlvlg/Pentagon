import 'package:flutter/material.dart';
import 'package:pentagon/controllers/profile_controller.dart' as controller;
import 'package:pentagon/exceptions/http_exception.dart';
import 'package:pentagon/models/profile.dart';

class ProfileProvider with ChangeNotifier {
  final String _token;
  List<Profile> _profiles = [];
  Profile? _profile;

  List<Profile> get profiles => [..._profiles];
  int get itemCount => _profiles.length;
  Profile? get profile => _profile;

  ProfileProvider([
    this._token = '',
    this._profile,
    this._profiles = const [],
  ]);

  Future<void> loadProfile({String? id, String? username}) async {
    _profile = await controller.ProfileController.getProfile(id, username,
        token: _token);
    notifyListeners();
  }

  Future<void> search(String text) async {
    try {
      _profiles =
          await controller.ProfileController.getProfiles(text, token: _token);
    } on HttpException catch (error) {
      switch (error.statusCode) {
        case 404:
          _profiles = [];
      }
    }

    notifyListeners();
  }
}

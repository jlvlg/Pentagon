import 'package:flutter/material.dart';
import 'package:pentagon/controllers/profile_controller.dart';
import 'package:pentagon/controllers/score_controller.dart';
import 'package:pentagon/exceptions/api_exception.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/models/score.dart';

class ProfileProvider with ChangeNotifier {
  final String _token;
  List<Profile> _profiles = [];
  Profile? _profile;
  final Profile? _authProfile;

  String get token => _token;
  List<Profile> get profiles => [..._profiles];
  int get itemCount => _profiles.length;
  Profile? get profile => _profile;
  Profile get authProfile => _authProfile!;

  ProfileProvider([
    this._token = '',
    this._profile,
    this._profiles = const [],
    this._authProfile,
  ]);

  Future<void> loadProfile({String? id, String? username}) async {
    _profile = await ProfileController.getProfile(
        id: id, username: username, token: _token);
    notifyListeners();
  }

  Future<void> search(String text) async {
    try {
      _profiles = await ProfileController.getProfiles(text, token: _token);
    } on ApiException catch (error) {
      switch (error.statusCode) {
        case 404:
          _profiles = [];
      }
    }

    notifyListeners();
  }

  Future<List<Score>> loadScores(Profile profile) async {
    return await ScoreController.loadScores(profile, authProfile.user,
        token: _token);
  }
}

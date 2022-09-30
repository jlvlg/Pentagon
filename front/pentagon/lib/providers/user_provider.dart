
import 'package:flutter/material.dart';
import 'package:pentagon/exceptions/http_exception.dart';
import 'package:pentagon/models/user.dart';
import 'package:pentagon/controllers/user_controller.dart';

class UserProvider with ChangeNotifier {
  final String _token;
  List<User> _users = [];
  User? _user;

  List<User> get users => [..._users];
  int get itemCount => _users.length;
  User? get user => _user;

  UserProvider([
    this._token = '',
    this._user,
    this._users = const [],
  ]);

  Future<void> loadUser({String? id, String? username}) async {
    _user = await UserController.getUser(username, id, token: _token);
    notifyListeners();
  }

  Future<void> search(String username) async {
    try {
      _users = await UserController.getUsers(username, token: _token);
    } on HttpException catch (error) {
      switch (error.statusCode) {
        case 404:
          _users = [];
      }
    }

    notifyListeners();
  }
}

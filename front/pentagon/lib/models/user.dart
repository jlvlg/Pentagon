import 'dart:convert';

import 'package:pentagon/models/auth.dart';

class User {
  final String id;
  final int followers;
  final String? joinDate;
  final Auth auth;

  User({
    required this.id,
    required this.auth,
    this.followers = 0,
    this.joinDate,
  });

  get toMap => {
        'id': id,
        'followers': followers,
        'joinDate': joinDate,
        'auth': auth.toMap,
      };

  get toJson => jsonEncode(toMap);

  factory User.fromMap(Map<String, dynamic> body, [String? token]) {
    return User(
      id: body['id'].toString(),
      auth: Auth(
        id: body['auth']['id'].toString(),
        token: token,
        username: body['auth']['username'],
      ),
      followers: body['followers'],
      joinDate: body['joinDate'],
    );
  }

  factory User.fromJson(String source) => User.fromMap(jsonDecode(source));
}

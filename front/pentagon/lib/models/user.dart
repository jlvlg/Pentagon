import 'dart:convert';

import 'package:pentagon/models/auth.dart';

class User {
  final String id;
  final List<User> following;
  final int followers;
  final String? joinDate;
  final Auth auth;

  User({
    required this.id,
    required this.auth,
    this.following = const [],
    this.followers = 0,
    this.joinDate,
  });

  get toMap => {
        'id': id,
        'following': following,
        'followers': followers,
        'joinDate': joinDate,
        'auth': auth.toMap,
      };

  get toJson => jsonEncode(toMap);

  static User fromMap(Map<String, dynamic> body, [String? token]) {
    return User(
      id: body['id'].toString(),
      auth: Auth(
        token: token,
        username: body['auth']['username'],
      ),
      followers: body['followers'],
      following: [...body['following'].map((e) => fromMap(e))],
      joinDate: body['joinDate'],
    );
  }
}

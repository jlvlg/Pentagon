import 'dart:convert';

class Auth {
  final String? token;
  final String username;

  Auth({
    this.token,
    required this.username,
  });

  get toMap => {'token': token ?? '', 'username': username};
  get toJson => jsonEncode(toMap);
}

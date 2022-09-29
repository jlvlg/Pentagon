import 'dart:convert';

class Auth {
  String id;
  String token;
  String username;

  Auth({
    required this.id,
    required this.token,
    required this.username,
  });

  get toMap => {'id': id, 'token': token, 'username': username};
  get toJson => jsonEncode(toMap);
}

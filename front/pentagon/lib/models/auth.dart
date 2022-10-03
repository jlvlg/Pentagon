import 'dart:convert';

class Auth {
  final String id;
  final String? token;
  final String username;

  Auth({
    this.token,
    required this.id,
    required this.username,
  });

  get toMap => {'id': id, 'token': token ?? '', 'username': username};
  get toJson => jsonEncode(toMap);

  factory Auth.fromMap(Map<String, dynamic> body) {
    return Auth(
      id: body['id'].toString(),
      token: body['token'],
      username: body['username'],
    );
  }

  factory Auth.fromJson(String source) => Auth.fromMap(jsonDecode(source));
}

import 'dart:convert';

import 'package:pentagon/models/user.dart';

class Profile {
  final String id;
  final User user;
  final String name;
  final String? image;
  final String? description;
  final List<dynamic> scoreMeans;

  Profile({
    required this.id,
    required this.user,
    required this.name,
    this.description,
    required this.scoreMeans,
    this.image,
  });

  get toMap => {
        'id': id,
        'user': user,
        'name': name,
        'description': description,
        'scoreMeans': scoreMeans,
        'image': image,
      };
  get toJson => jsonDecode(toMap);

  static Profile fromMap(Map<String, dynamic> body) {
    return Profile(
      id: body['id'].toString(),
      name: body['name'],
      user: User.fromMap(body['user']),
      description: body['description'],
      scoreMeans: body['scoreMeans'],
      image: body['image'],
    );
  }
}

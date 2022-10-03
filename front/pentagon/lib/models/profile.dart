import 'dart:convert';

import 'package:pentagon/models/user.dart';

class Profile {
  final String id;
  User user;
  String name;
  String? image;
  String? description;
  final double appearanceScore;
  final double intelligenceScore;
  final double characterScore;
  final double humorScore;
  final double responsibilityScore;

  Profile({
    required this.id,
    required this.user,
    required this.name,
    this.description,
    required this.appearanceScore,
    required this.intelligenceScore,
    required this.characterScore,
    required this.humorScore,
    required this.responsibilityScore,
    this.image,
  });

  get toMap => {
        'id': id,
        'user': user.toMap,
        'name': name,
        'description': description,
        'appearanceScore': appearanceScore,
        'intelligenceScore': intelligenceScore,
        'characterScore': characterScore,
        'humorScore': humorScore,
        'responsibilityScore': responsibilityScore,
        'image': image,
      };
  get toJson => jsonDecode(toMap);

  List<double> get score => [
        appearanceScore + 5,
        intelligenceScore + 5,
        characterScore + 5,
        humorScore + 5,
        responsibilityScore + 5,
      ];

  factory Profile.fromMap(Map<String, dynamic> body, [String? token]) {
    return Profile(
      id: body['id'].toString(),
      name: body['name'],
      user: User.fromMap(body['user'], token),
      description: body['description'],
      appearanceScore: body['appearanceScore'],
      intelligenceScore: body['intelligenceScore'],
      characterScore: body['characterScore'],
      humorScore: body['humorScore'],
      responsibilityScore: body['responsibilityScore'],
      image: body['image'],
    );
  }

  factory Profile.fromJson(String source, [String? token]) =>
      Profile.fromMap(jsonDecode(source), token);
}

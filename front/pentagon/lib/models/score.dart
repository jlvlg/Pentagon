import 'dart:convert';

import 'package:pentagon/models/profile.dart';
import 'package:pentagon/models/user.dart';

class Score {
  String id;
  int score;
  User author;
  Profile profile;
  String category;

  Score({
    required this.id,
    required this.score,
    required this.author,
    required this.profile,
    required this.category,
  });

  get toMap => {
        'id': id,
        'score': score,
        'author': author.toMap,
        'profile': profile.toMap,
        'category': category,
      };

  get toJson => jsonDecode(toMap);

  factory Score.fromMap(Map<String, dynamic> body) {
    return Score(
      id: body['id'].toString(),
      score: int.parse(body['score']),
      author: User.fromMap(body['author']),
      profile: Profile.fromMap(body['profile']),
      category: body['category'],
    );
  }
}

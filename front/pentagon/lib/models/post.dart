import 'dart:convert';

import 'package:pentagon/models/user.dart';

class Post {
  final String id;
  final User author;
  String title;
  String text;
  String? image;
  final String creationDate;
  int likes;
  int unlikes;
  final bool edited;

  Post({
    required this.id,
    required this.author,
    required this.title,
    required this.creationDate,
    required this.edited,
    this.image,
    required this.likes,
    required this.text,
    required this.unlikes,
  });

  get toMap => {
        'id': id,
        'author': author.toMap,
        'creationDate': creationDate,
        'edited': edited,
        'likes': likes,
        'title': title,
        'text': text,
        'image': image,
        'unlikes': unlikes,
      };

  get toJson => jsonEncode(toMap);

  factory Post.fromMap(Map<String, dynamic> map) {
    return Post(
      id: map['id'].toString(),
      author: User.fromMap(map['author']),
      title: map['title'],
      creationDate: map['creationDate'],
      edited: map['edited'],
      image: map['image'],
      likes: map['likes'],
      text: map['text'],
      unlikes: map['unlikes'],
    );
  }

  factory Post.fromJson(String source) => Post.fromMap(jsonDecode(source));
}

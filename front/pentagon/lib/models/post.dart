import 'package:pentagon/models/user.dart';

class Post {
  int id;
  User author;
  String text;
  DateTime creationDate;
  int likes;
  int unlikes;
  bool isEdited;

  Post({
    required this.id,
    required this.author,
    required this.creationDate,
    required this.isEdited,
    required this.likes,
    required this.text,
    required this.unlikes,
  });
}

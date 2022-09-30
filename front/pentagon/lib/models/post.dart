import 'package:pentagon/models/user.dart';

class Post {
  final int id;
  final User author;
  final String text;
  final DateTime creationDate;
  final int likes;
  final int unlikes;
  final bool isEdited;

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

class User {
  String id;
  String username;
  List<User> following;
  int followers;
  DateTime joinDate;

  User({
    required this.id,
    required this.username,
    required this.following,
    required this.followers,
    required this.joinDate,
  });
}

class User {
  int id;
  String username;
  List<User> following;
  List<User> followed;

  User({
    required this.id,
    required this.username,
    required this.following,
    required this.followed,
  });
}

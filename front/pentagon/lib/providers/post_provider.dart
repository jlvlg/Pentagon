import 'package:flutter/material.dart';
import 'package:pentagon/controllers/post_controller.dart';
import 'package:pentagon/exceptions/api_exception.dart';
import 'package:pentagon/models/post.dart';
import 'package:pentagon/models/profile.dart';

class PostProvider with ChangeNotifier {
  final String _token;
  final Profile? _authProfile;
  List<Post> _posts = [];
  Post? _post;

  String get token => _token;
  List<Post> get posts => [..._posts];
  int get itemCount => _posts.length;
  Post? get post => _post;
  Profile get authProfile => _authProfile!;

  PostProvider([
    this._token = '',
    this._post,
    this._authProfile,
    this._posts = const [],
  ]);

  Future<void> loadPost(String id) async {
    _post = await PostController.getPost(id, token: _token);
    notifyListeners();
  }

  Future<void> getPosts(Profile profile) async {
    try {
      _posts = await PostController.getPosts(profile, token: _token);
    } on ApiException catch (error) {
      switch (error.statusCode) {
        case 404:
          _posts = [];
      }
    }
    notifyListeners();
  }

  Future<void> getFollowing() async {
    try {
      _posts = await PostController.getFollowing(_authProfile!, token: _token);
    } on ApiException catch (error) {
      switch (error.statusCode) {
        case 404:
          _posts = [];
      }
    }
    notifyListeners();
  }
}

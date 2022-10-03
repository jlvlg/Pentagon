import 'dart:convert';
import 'dart:io';

import 'package:firebase_storage/firebase_storage.dart';
import 'package:pentagon/exceptions/api_exception.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/models/post.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/util/constants/api_endpoints.dart';
import 'package:pentagon/util/helpers/api.dart';

class PostController {
  static Future<Post> getPost(String id, {String token = ''}) async {
    final response = await Api.get(
      ApiEndpoints.posts,
      token: token,
      query: {
        'id': id,
      },
    );

    switch (response.statusCode) {
      case 400:
        throw ApiException(400, S.current.badRequest);
      case 403:
        throw ApiException(403, S.current.accessForbidden);
      case 404:
        throw ApiException(404, S.current.objectNotFound(S.current.post));
    }

    return Post.fromJson(const Utf8Decoder().convert(response.bodyBytes));
  }

  static Future<List<Post>> getPosts(Profile profile,
      {String token = ''}) async {
    final response = await Api.get(
      ApiEndpoints.posts,
      token: token,
      query: {
        'author': profile.user.id,
      },
    );

    switch (response.statusCode) {
      case 400:
        throw ApiException(400, S.current.badRequest);
      case 403:
        throw ApiException(403, S.current.accessForbidden);
    }

    return (jsonDecode(const Utf8Decoder().convert(response.bodyBytes)) as List)
        .map((e) => Post.fromMap(e))
        .toList();
  }

  static Future<Post> postPost(Profile profile, String title, String text,
      {String token = '', File? image}) async {
    final response = await Api.post(
      ApiEndpoints.posts,
      token: token,
      data: {
        'profile': profile.toMap,
        'author': profile.user.toMap,
        'title': title,
        'text': text,
      },
    );

    switch (response.statusCode) {
      case 403:
        throw ApiException(403, S.current.accessForbidden);
      case 422:
        throw ApiException(
            422, S.current.invalidFieldException(S.current.title));
    }

    Post post = Post.fromJson(const Utf8Decoder().convert(response.bodyBytes));

    if (image != null) {
      post = await patchPost(post, token: token, image: image);
    }

    return post;
  }

  static Future<List<Post>> getFollowing(Profile profile,
      {String token = ''}) async {
    final response = await Api.get(
      ApiEndpoints.followedPosts,
      token: token,
      query: {
        'user': profile.user.id,
      },
    );

    switch (response.statusCode) {
      case 400:
        throw ApiException(400, S.current.badRequest);
      case 403:
        throw ApiException(403, S.current.accessForbidden);
      case 404:
        throw ApiException(404, S.current.noObjectfound(S.current.post));
    }

    final body = jsonDecode(const Utf8Decoder().convert(response.bodyBytes));

    return (body as List<dynamic>).map((e) => Post.fromMap(e)).toList();
  }

  static Future<Post> patchPost(Post post,
      {String token = '', File? image}) async {
    if (image != null) {
      final storage = FirebaseStorage.instance.ref();
      final profileImagePath = storage.child('posts/${post.id}.jpg');
      await profileImagePath.putFile(image);

      post.image = await profileImagePath.getDownloadURL();
    }

    final response = await Api.patch(
      ApiEndpoints.posts,
      token: token,
      data: post.toMap,
    );

    switch (response.statusCode) {
      case 403:
        throw ApiException(403, S.current.accessForbidden);
      case 422:
        throw ApiException(
            422, S.current.invalidFieldException(S.current.title));
    }

    return Post.fromJson(const Utf8Decoder().convert(response.bodyBytes));
  }
}

import 'dart:convert';
import 'dart:io';

import 'package:firebase_storage/firebase_storage.dart';
import 'package:pentagon/exceptions/api_exception.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/util/constants/api_endpoints.dart';
import 'package:pentagon/util/helpers/api.dart';

class ProfileController {
  static Future<Profile> getProfile(
      {String? id,
      String? username,
      String token = '',
      bool setToken = false}) async {
    final Map<String, dynamic> request = {};

    if (id != null) {
      request['id'] = id;
    }
    if (username != null) {
      request['username'] = username;
    }

    final response = await Api.get(
      ApiEndpoints.profiles,
      query: request,
      token: token,
    );

    switch (response.statusCode) {
      case 400:
        throw ApiException(400, S.current.badRequest);
      case 403:
        throw ApiException(403, S.current.accessForbidden);
      case 404:
        throw ApiException(404, S.current.objectNotFound(S.current.profile));
    }

    return Profile.fromJson(const Utf8Decoder().convert(response.bodyBytes),
        setToken ? token : null);
  }

  static Future<List<Profile>> getProfiles(String text,
      {String token = ''}) async {
    final response = await Api.get(
      ApiEndpoints.pageSearch,
      query: {'text': text},
      token: token,
    );

    switch (response.statusCode) {
      case 400:
        throw ApiException(400, S.current.badRequest);
      case 403:
        throw ApiException(403, S.current.accessForbidden);
      case 404:
        throw ApiException(404, S.current.noObjectfound(S.current.profile));
    }

    final body = jsonDecode(const Utf8Decoder().convert(response.bodyBytes));

    return (body as List<dynamic>).map((e) => Profile.fromMap(e)).toList();
  }

  static Future<Profile> patchProfile(Profile profile,
      {File? image, String token = '', bool setToken = false}) async {
    if (image != null) {
      final storage = FirebaseStorage.instance.ref();
      final profileImagePath = storage.child('profiles/${profile.id}.jpg');
      await profileImagePath.putFile(image);

      profile.image = await profileImagePath.getDownloadURL();
    }

    final response = await Api.patch(
      ApiEndpoints.profiles,
      token: token,
      data: profile.toMap,
    );

    switch (response.statusCode) {
      case 403:
        throw ApiException(403, S.current.accessForbidden);
      case 404:
        throw ApiException(404, S.current.objectNotFound(S.current.profile));
      case 422:
        throw ApiException(
          422,
          S.current.invalidFieldException(S.current.name),
        );
    }

    return Profile.fromJson(const Utf8Decoder().convert(response.bodyBytes),
        setToken ? token : null);
  }

  static Future<void> deleteProfile(Profile profile,
      {String token = ''}) async {
    final response = await Api.delete(
      ApiEndpoints.profiles,
      data: profile.toMap,
      token: token,
    );

    switch (response.statusCode) {
      case 403:
        throw ApiException(403, S.current.accessForbidden);
      case 404:
        throw ApiException(404, S.current.objectNotFound(S.current.profile));
    }
  }
}

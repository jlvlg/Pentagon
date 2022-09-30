import 'dart:convert';

import 'package:pentagon/exceptions/http_exception.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/util/constants/api_endpoints.dart';
import 'package:pentagon/util/helpers/api.dart';

class ProfileController {
  static Future<Profile> getProfile(String? id, String? username,
      {String token = ''}) async {
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
        throw HttpException(400, S.current.badRequest);
      case 403:
        throw HttpException(403, S.current.accessForbidden);
      case 404:
        throw HttpException(404, S.current.objectNotFound(S.current.profile));
    }

    return Profile.fromMap(jsonDecode(response.body));
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
        throw HttpException(400, S.current.badRequest);
      case 403:
        throw HttpException(403, S.current.accessForbidden);
      case 404:
        throw HttpException(404, S.current.noObjectfound(S.current.profile));
    }

    final body = jsonDecode(response.body);

    return (body as List<dynamic>).map((e) => Profile.fromMap(e)).toList();
  }
}

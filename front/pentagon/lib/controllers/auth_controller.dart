import 'dart:convert';

import 'package:pentagon/exceptions/api_exception.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/util/constants/api_endpoints.dart';
import 'package:pentagon/util/helpers/api.dart';

class AuthController {
  static Future<Profile> authenticate(
      String username, String password, String urlFragment) async {
    final response = await Api.post(
      '${ApiEndpoints.auth}/$urlFragment',
      data: {
        'username': username,
        'password': password,
      },
    );

    switch (response.statusCode) {
      case 403:
        throw ApiException(403, S.current.authExceptionMsg);
      case 409:
        throw ApiException(404, S.current.usernameTakenException);
      case 422:
        throw ApiException(
            422, S.current.invalidFieldException(S.current.username));
    }

    return Profile.fromJson(const Utf8Decoder().convert(response.bodyBytes),
        response.headers['authorization']);
  }
}

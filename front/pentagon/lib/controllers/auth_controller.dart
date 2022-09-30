import 'dart:convert';

import 'package:pentagon/exceptions/http_exception.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/models/user.dart';
import 'package:pentagon/util/constants/api_endpoints.dart';
import 'package:pentagon/util/helpers/api.dart';

class AuthController {
  static Future<User> authenticate(
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
        throw HttpException(403, S.current.authExceptionMsg);
      case 409:
        throw HttpException(404, S.current.usernameTakenException);
      case 422:
        throw HttpException(
            422, S.current.invalidFieldException(S.current.username));
    }

    final body = jsonDecode(response.body);

    return User.fromMap(body, response.headers['authorization']);
  }
}

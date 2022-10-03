import 'dart:convert';

import 'package:pentagon/exceptions/api_exception.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/models/score.dart';
import 'package:pentagon/models/user.dart';
import 'package:pentagon/util/constants/api_endpoints.dart';
import 'package:pentagon/util/helpers/api.dart';

class ScoreController {
  static Future<void> vote(
      Profile profile, String category, User author, int score,
      {String token = ''}) async {
    final response = await Api.post(
      ApiEndpoints.score,
      token: token,
      data: {
        'category': category,
        'author': author.id,
        'score': score,
        'target': profile.id,
      },
    );

    switch (response.statusCode) {
      case 403:
        throw ApiException(403, S.current.accessForbidden);
      case 404:
        throw ApiException(404, S.current.objectNotFound(S.current.profile));
    }
  }

  static Future<List<Score>> loadScores(Profile profile, User author,
      {String token = ''}) async {
    final response = await Api.get(
      ApiEndpoints.profileScores,
      token: token,
      query: {
        'profile': profile.id,
        'author': author.id,
      },
    );

    switch (response.statusCode) {
      case 403:
        throw ApiException(403, S.current.accessForbidden);
      case 404:
        throw ApiException(404, S.current.objectNotFound(S.current.profile));
    }

    return (jsonDecode(response.body) as List)
        .map((e) => Score.fromMap(e))
        .toList();
  }
}

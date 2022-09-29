import 'package:http/http.dart' as http;
import 'package:pentagon/util/constants/api_endpoints.dart';
import 'package:pentagon/util/helpers/uri_parser.dart';

class Api {
  static Future<http.Response> post(
    String url, {
    Map<String, dynamic>? data,
    Map<String, dynamic>? query,
  }) async {
    return UriParser.post(
      Uri(
        scheme: ApiEndpoints.scheme,
        host: ApiEndpoints.host,
        port: ApiEndpoints.port,
        path: '${ApiEndpoints.api}/$url',
        queryParameters: query,
      ).toString(),
      data,
    );
  }
}

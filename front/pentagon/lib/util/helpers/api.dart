import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:pentagon/util/constants/api_endpoints.dart';

class Api {
  static Future<http.Response> post(
    String url, {
    String token = '',
    Map<String, dynamic>? data,
    Map<String, dynamic>? query,
  }) async {
    return http.post(
      Uri(
        scheme: ApiEndpoints.scheme,
        host: ApiEndpoints.host,
        port: ApiEndpoints.port,
        path: '${ApiEndpoints.api}/$url',
        queryParameters: query,
      ),
      headers: {
        'content-type': 'application/json',
        'authorization': 'Bearer $token'
      },
      body: jsonEncode(data),
    );
  }

  static Future<http.Response> get(
    String url, {
    String token = '',
    Map<String, dynamic>? query,
  }) async {
    return http.get(
      Uri(
        scheme: ApiEndpoints.scheme,
        host: ApiEndpoints.host,
        port: ApiEndpoints.port,
        path: '${ApiEndpoints.api}/$url',
        queryParameters: query,
      ),
      headers: {'authorization': 'Bearer $token'},
    );
  }

  static Future<http.Response> patch(
    String url, {
    String token = '',
    Map<String, dynamic>? data,
    Map<String, dynamic>? query,
  }) async {
    return http.patch(
      Uri(
        scheme: ApiEndpoints.scheme,
        host: ApiEndpoints.host,
        port: ApiEndpoints.port,
        path: '${ApiEndpoints.api}/$url',
        queryParameters: query,
      ),
      headers: {
        'content-type': 'application/json',
        'authorization': 'Bearer $token'
      },
      body: jsonEncode(data),
    );
  }

  static Future<http.Response> delete(
    String url, {
    String token = '',
    Map<String, dynamic>? query,
    Map<String, dynamic>? data,
  }) async {
    return http.delete(
      Uri(
        scheme: ApiEndpoints.scheme,
        host: ApiEndpoints.host,
        port: ApiEndpoints.port,
        path: '${ApiEndpoints.api}/$url',
        queryParameters: query,
      ),
      headers: {
        'content-type': 'application/json',
        'authorization': 'Bearer $token',
      },
      body: jsonEncode(data),
    );
  }
}

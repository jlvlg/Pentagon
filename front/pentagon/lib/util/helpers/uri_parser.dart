import 'dart:convert';

import 'package:http/http.dart' as http;

class UriParser {
  static Future<http.Response> post(
      String url, Map<String, dynamic>? data) async {
    return await http.post(
      Uri.parse(url),
      body: jsonEncode(data),
      headers: {'Content-Type': 'application/json'},
    );
  }
}

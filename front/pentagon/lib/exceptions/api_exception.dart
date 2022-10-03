class ApiException {
  final String msg;
  final int statusCode;

  ApiException(this.statusCode, this.msg);

  @override
  String toString() {
    return msg;
  }
}

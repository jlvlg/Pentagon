class HttpException {
  final String msg;
  final int statusCode;

  HttpException(this.statusCode, this.msg);

  @override
  String toString() {
    return msg;
  }
}

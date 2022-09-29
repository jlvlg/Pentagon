class AuthException {
  final String msg;

  AuthException(this.msg);

  @override
  String toString() {
    return msg;
  }
}

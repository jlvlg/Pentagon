// DO NOT EDIT. This is code generated via package:intl/generate_localized.dart
// This is a library that provides messages for a pt locale. All the
// messages from the main program should be duplicated here with the same
// function name.

// Ignore issues from commonly used lints in this file.
// ignore_for_file:unnecessary_brace_in_string_interps, unnecessary_new
// ignore_for_file:prefer_single_quotes,comment_references, directives_ordering
// ignore_for_file:annotate_overrides,prefer_generic_function_type_aliases
// ignore_for_file:unused_import, file_names, avoid_escaping_inner_quotes
// ignore_for_file:unnecessary_string_interpolations, unnecessary_string_escapes

import 'package:intl/intl.dart';
import 'package:intl/message_lookup_by_library.dart';

final messages = new MessageLookup();

typedef String MessageIfAbsent(String messageStr, List<dynamic> args);

class MessageLookup extends MessageLookupByLibrary {
  String get localeName => 'pt';

  static String m0(field) => "${field} é obrigatório(a)";

  static String m1(field) => "${field} inválido";

  static String m2(error) => "Ocorreu um erro ${error}";

  static String m3(error, length) =>
      "Tamanho ${error} são ${length} caracteres";

  final messages = _notInlinedMessages(_notInlinedMessages);
  static Map<String, Function> _notInlinedMessages(_) => <String, Function>{
        "authExceptionMsg": MessageLookupByLibrary.simpleMessage(
            "Falha na autenticação, seu nome de usuário ou senha podem estar incorretos"),
        "confirmPassword":
            MessageLookupByLibrary.simpleMessage("Confirmar senha"),
        "emptyFieldValidation": m0,
        "invalidFieldException": m1,
        "invalidFieldValidation": MessageLookupByLibrary.simpleMessage(
            "Campo contém caracteres inválidos"),
        "login": MessageLookupByLibrary.simpleMessage("Login"),
        "maximum": MessageLookupByLibrary.simpleMessage("Máximo"),
        "minimum": MessageLookupByLibrary.simpleMessage("Mínimo"),
        "onError": m2,
        "password": MessageLookupByLibrary.simpleMessage("Senha"),
        "passwordInvalidLength": m3,
        "passwordsDoNotMatch":
            MessageLookupByLibrary.simpleMessage("Senhas não coincidem"),
        "signup": MessageLookupByLibrary.simpleMessage("Registrar"),
        "unexpected": MessageLookupByLibrary.simpleMessage("inesperado"),
        "username": MessageLookupByLibrary.simpleMessage("Usuário"),
        "usernameTakenException":
            MessageLookupByLibrary.simpleMessage("Nome de usuário em uso")
      };
}

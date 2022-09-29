// DO NOT EDIT. This is code generated via package:intl/generate_localized.dart
// This is a library that provides messages for a en locale. All the
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
  String get localeName => 'en';

  static String m0(field) => "${field} is a required field";

  static String m1(field) => "Invalid ${field}";

  static String m2(error) => "An ${error} error has ocurred";

  static String m3(error, length) => "${error} length is ${length}";

  final messages = _notInlinedMessages(_notInlinedMessages);
  static Map<String, Function> _notInlinedMessages(_) => <String, Function>{
        "authExceptionMsg": MessageLookupByLibrary.simpleMessage(
            "Authentication failure, your username or password might be incorrect"),
        "confirmPassword":
            MessageLookupByLibrary.simpleMessage("Confirm password"),
        "emptyFieldValidation": m0,
        "invalidFieldException": m1,
        "invalidFieldValidation": MessageLookupByLibrary.simpleMessage(
            "Field contains invalid characters"),
        "login": MessageLookupByLibrary.simpleMessage("Login"),
        "maximum": MessageLookupByLibrary.simpleMessage("Maximum"),
        "minimum": MessageLookupByLibrary.simpleMessage("Minimum"),
        "onError": m2,
        "password": MessageLookupByLibrary.simpleMessage("Password"),
        "passwordInvalidLength": m3,
        "passwordsDoNotMatch":
            MessageLookupByLibrary.simpleMessage("Passwords do not match"),
        "signup": MessageLookupByLibrary.simpleMessage("Sign Up"),
        "unexpected": MessageLookupByLibrary.simpleMessage("unexpected"),
        "username": MessageLookupByLibrary.simpleMessage("Username"),
        "usernameTakenException":
            MessageLookupByLibrary.simpleMessage("Username taken")
      };
}

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

  static String m2(object) => "No ${object} found";

  static String m3(object) => "${object} not found";

  static String m4(error) => "An ${error} error has ocurred";

  static String m5(error, length) => "${error} length is ${length}";

  final messages = _notInlinedMessages(_notInlinedMessages);
  static Map<String, Function> _notInlinedMessages(_) => <String, Function>{
        "accessForbidden":
            MessageLookupByLibrary.simpleMessage("Access forbidden"),
        "account": MessageLookupByLibrary.simpleMessage("Account"),
        "alreadyFollowing":
            MessageLookupByLibrary.simpleMessage("Already following"),
        "appearance": MessageLookupByLibrary.simpleMessage("Appearance"),
        "authExceptionMsg": MessageLookupByLibrary.simpleMessage(
            "Authentication failure, your username or password might be incorrect"),
        "badRequest": MessageLookupByLibrary.simpleMessage("Bad request"),
        "body": MessageLookupByLibrary.simpleMessage("Body"),
        "camera": MessageLookupByLibrary.simpleMessage("Camera"),
        "cancel": MessageLookupByLibrary.simpleMessage("Cancel"),
        "changePassword":
            MessageLookupByLibrary.simpleMessage("Change password"),
        "character": MessageLookupByLibrary.simpleMessage("Character"),
        "confirmPassword":
            MessageLookupByLibrary.simpleMessage("Confirm password"),
        "cropImage": MessageLookupByLibrary.simpleMessage("Crop image"),
        "currentPassword":
            MessageLookupByLibrary.simpleMessage("Current password"),
        "delete": MessageLookupByLibrary.simpleMessage("Delete"),
        "deleteAccountMsg": MessageLookupByLibrary.simpleMessage(
            "Are you sure you want to delete your account? This action cannot be undone."),
        "description": MessageLookupByLibrary.simpleMessage("Description"),
        "edit": MessageLookupByLibrary.simpleMessage("Edit"),
        "emptyFieldValidation": m0,
        "gallery": MessageLookupByLibrary.simpleMessage("Gallery"),
        "home": MessageLookupByLibrary.simpleMessage("Home"),
        "humor": MessageLookupByLibrary.simpleMessage("Humor"),
        "intelligence": MessageLookupByLibrary.simpleMessage("Intelligence"),
        "invalidFieldException": m1,
        "invalidFieldValidation": MessageLookupByLibrary.simpleMessage(
            "Field contains invalid characters"),
        "language": MessageLookupByLibrary.simpleMessage("Language"),
        "login": MessageLookupByLibrary.simpleMessage("Login"),
        "logout": MessageLookupByLibrary.simpleMessage("Logout"),
        "maximum": MessageLookupByLibrary.simpleMessage("Maximum"),
        "minimum": MessageLookupByLibrary.simpleMessage("Minimum"),
        "name": MessageLookupByLibrary.simpleMessage("Name"),
        "noObjectfound": m2,
        "notFollowing": MessageLookupByLibrary.simpleMessage("Not following"),
        "objectNotFound": m3,
        "offline": MessageLookupByLibrary.simpleMessage("Offline"),
        "onError": m4,
        "password": MessageLookupByLibrary.simpleMessage("Password"),
        "passwordInvalidLength": m5,
        "passwordsDoNotMatch":
            MessageLookupByLibrary.simpleMessage("Passwords do not match"),
        "post": MessageLookupByLibrary.simpleMessage("Post"),
        "profile": MessageLookupByLibrary.simpleMessage("Profile"),
        "responsibility":
            MessageLookupByLibrary.simpleMessage("Responsibility"),
        "save": MessageLookupByLibrary.simpleMessage("Save"),
        "settings": MessageLookupByLibrary.simpleMessage("Settings"),
        "signup": MessageLookupByLibrary.simpleMessage("Sign Up"),
        "source": MessageLookupByLibrary.simpleMessage("Source"),
        "title": MessageLookupByLibrary.simpleMessage("Title"),
        "toPost": MessageLookupByLibrary.simpleMessage("Post"),
        "unexpected": MessageLookupByLibrary.simpleMessage("unexpected"),
        "user": MessageLookupByLibrary.simpleMessage("User"),
        "username": MessageLookupByLibrary.simpleMessage("Username"),
        "usernameTakenException":
            MessageLookupByLibrary.simpleMessage("Username taken")
      };
}

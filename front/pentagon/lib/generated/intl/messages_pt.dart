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

  static String m2(object) => "Nenhum ${object} encontrado";

  static String m3(object) => "${object} não encontrado";

  static String m4(error) => "Ocorreu um erro ${error}";

  static String m5(error, length) =>
      "Tamanho ${error} são ${length} caracteres";

  final messages = _notInlinedMessages(_notInlinedMessages);
  static Map<String, Function> _notInlinedMessages(_) => <String, Function>{
        "accessForbidden":
            MessageLookupByLibrary.simpleMessage("Acesso proibido"),
        "account": MessageLookupByLibrary.simpleMessage("Conta"),
        "alreadyFollowing":
            MessageLookupByLibrary.simpleMessage("Você já segue este usuário"),
        "appearance": MessageLookupByLibrary.simpleMessage("Aparência"),
        "authExceptionMsg": MessageLookupByLibrary.simpleMessage(
            "Falha na autenticação, seu nome de usuário ou senha podem estar incorretos"),
        "badRequest":
            MessageLookupByLibrary.simpleMessage("Requisição não sucedida"),
        "body": MessageLookupByLibrary.simpleMessage("Texto"),
        "camera": MessageLookupByLibrary.simpleMessage("Câmera"),
        "cancel": MessageLookupByLibrary.simpleMessage("Cancelar"),
        "changePassword": MessageLookupByLibrary.simpleMessage("Alterar senha"),
        "character": MessageLookupByLibrary.simpleMessage("Caráter"),
        "confirmPassword":
            MessageLookupByLibrary.simpleMessage("Confirmar senha"),
        "cropImage": MessageLookupByLibrary.simpleMessage("Cortar imagem"),
        "currentPassword": MessageLookupByLibrary.simpleMessage("Senha atual"),
        "delete": MessageLookupByLibrary.simpleMessage("Deletar"),
        "deleteAccountMsg": MessageLookupByLibrary.simpleMessage(
            "Tem certeza que deseja deletar sua conta? Esta ação não pode ser desfeita"),
        "description": MessageLookupByLibrary.simpleMessage("Descrição"),
        "edit": MessageLookupByLibrary.simpleMessage("Editar"),
        "emptyFieldValidation": m0,
        "gallery": MessageLookupByLibrary.simpleMessage("Galeria"),
        "home": MessageLookupByLibrary.simpleMessage("Página inicial"),
        "humor": MessageLookupByLibrary.simpleMessage("Humor"),
        "intelligence": MessageLookupByLibrary.simpleMessage("Inteligência"),
        "invalidFieldException": m1,
        "invalidFieldValidation": MessageLookupByLibrary.simpleMessage(
            "Campo contém caracteres inválidos"),
        "language": MessageLookupByLibrary.simpleMessage("Linguagem"),
        "login": MessageLookupByLibrary.simpleMessage("Login"),
        "logout": MessageLookupByLibrary.simpleMessage("Sair"),
        "maximum": MessageLookupByLibrary.simpleMessage("Máximo"),
        "minimum": MessageLookupByLibrary.simpleMessage("Mínimo"),
        "name": MessageLookupByLibrary.simpleMessage("Nome"),
        "noObjectfound": m2,
        "notFollowing":
            MessageLookupByLibrary.simpleMessage("Você não segue este usuário"),
        "objectNotFound": m3,
        "offline": MessageLookupByLibrary.simpleMessage("Sem conexão"),
        "onError": m4,
        "password": MessageLookupByLibrary.simpleMessage("Senha"),
        "passwordInvalidLength": m5,
        "passwordsDoNotMatch":
            MessageLookupByLibrary.simpleMessage("Senhas não coincidem"),
        "post": MessageLookupByLibrary.simpleMessage("Postagem"),
        "profile": MessageLookupByLibrary.simpleMessage("Perfil"),
        "responsibility":
            MessageLookupByLibrary.simpleMessage("Responsabilidade"),
        "save": MessageLookupByLibrary.simpleMessage("Salvar"),
        "settings": MessageLookupByLibrary.simpleMessage("Configurações"),
        "signup": MessageLookupByLibrary.simpleMessage("Registrar"),
        "source": MessageLookupByLibrary.simpleMessage("Fonte"),
        "title": MessageLookupByLibrary.simpleMessage("Título"),
        "toPost": MessageLookupByLibrary.simpleMessage("Postar"),
        "unexpected": MessageLookupByLibrary.simpleMessage("inesperado"),
        "user": MessageLookupByLibrary.simpleMessage("Usuário"),
        "username": MessageLookupByLibrary.simpleMessage("Usuário"),
        "usernameTakenException":
            MessageLookupByLibrary.simpleMessage("Nome de usuário em uso")
      };
}

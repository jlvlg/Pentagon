import 'package:flutter/material.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/screens/auth_screen/components/text_input.dart';
import 'package:pentagon/util/components/accent_button.dart';

enum AuthMode {
  login,
  signup,
}

class AuthForm extends StatefulWidget {
  const AuthForm({super.key});

  @override
  State<AuthForm> createState() => _AuthFormState();
}

class _AuthFormState extends State<AuthForm> {
  final _key = GlobalKey<FormState>();
  final _data = Map<String, dynamic>;
  AuthMode _authMode = AuthMode.login;

  bool get _isLogin => _authMode == AuthMode.login;
  bool get _isSignup => _authMode == AuthMode.signup;

  void _submit() {
    if (_key.currentState?.validate() ?? false) {
      _key.currentState!.save();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      alignment: Alignment.topCenter,
      clipBehavior: Clip.none,
      children: [
        Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(25),
          ),
          child: Container(
            width: 300,
            padding: const EdgeInsets.symmetric(
              horizontal: 20,
              vertical: 15,
            ),
            child: Form(
              autovalidateMode: AutovalidateMode.onUserInteraction,
              child: Wrap(
                runSpacing: 10,
                children: [
                  TextInput(label: S.of(context).username),
                  TextInput(label: S.of(context).password),
                  if (_isSignup)
                    TextInput(label: S.of(context).confirmPassword),
                  LayoutBuilder(
                    builder: (context, constraints) => Wrap(
                      spacing: 10,
                      children: [
                        SizedBox(
                          width: (constraints.maxWidth - 10) * .5,
                          child: AccentButton(
                            label: S.of(context).login,
                            onPressed: _submit,
                          ),
                        ),
                        SizedBox(
                          width: (constraints.maxWidth - 10) * .5,
                          child: AccentButton(
                            label: S.of(context).signup,
                            onPressed: _isSignup
                                ? _submit
                                : () => setState(
                                      () => _authMode = AuthMode.signup,
                                    ),
                          ),
                        ),
                      ],
                    ),
                  )
                ],
              ),
            ),
          ),
        ),
        Positioned(
          top: -65,
          child: Hero(
            tag: 'icon',
            child: Image.asset(
              'assets/images/icons/icon-512x512.png',
              height: 90,
              width: 90,
            ),
          ),
        ),
      ],
    );
  }
}

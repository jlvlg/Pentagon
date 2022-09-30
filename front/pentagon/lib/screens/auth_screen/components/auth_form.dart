import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:pentagon/exceptions/http_exception.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/util/components/text_input.dart';
import 'package:pentagon/util/components/accent_button.dart';
import 'package:pentagon/util/constants/app_colors.dart';
import 'package:provider/provider.dart';

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
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();
  final _formKey = GlobalKey<FormState>();
  final _listKey = GlobalKey<AnimatedListState>();
  AuthMode _authMode = AuthMode.login;
  bool _isLoading = false;

  bool get _isLogin => _authMode == AuthMode.login;
  bool get _isSignup => _authMode == AuthMode.signup;

  Future<void> _submit() async {
    if (_formKey.currentState?.validate() ?? false) {
      setState(() {
        _isLoading = true;
      });

      _formKey.currentState!.save();

      AuthProvider auth = Provider.of(context, listen: false);

      try {
        if (_isLogin) {
          await auth.login(_usernameController.text, _passwordController.text);
        } else {
          await auth.signUp(_usernameController.text, _passwordController.text);
        }
      } on HttpException catch (error) {
        _showErrorDialog(error.toString());
        // } catch (error) {
        //   _showErrorDialog(S.of(context).onError(S.of(context).unexpected));
      }

      setState(() {
        _isLoading = false;
      });
    }
  }

  void _switchAuthMode() {
    setState(() {
      if (_isLogin) {
        _authMode = AuthMode.signup;
        _listKey.currentState?.insertItem(2);
      } else {
        _authMode = AuthMode.login;
        _listKey.currentState?.removeItem(
          2,
          (context, animation) => SizeTransition(
            sizeFactor: CurvedAnimation(
              parent: animation,
              curve: Curves.ease,
            ),
            child: TextInput(
              label: S.of(context).confirmPassword,
              margin: const EdgeInsets.only(bottom: 10),
              obscureText: true,
              textInputAction: TextInputAction.done,
              onSubmitted: (_) => _submit(),
              validator: (text) {
                final password = text ?? '';
                if (password != _passwordController.text) {
                  return S.of(context).passwordsDoNotMatch;
                }
                return null;
              },
            ),
          ),
        );
      }
    });
  }

  void _showErrorDialog(String msg) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(msg),
        duration: const Duration(seconds: 2),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final local = S.of(context);
    final fields = [
      TextInput(
        label: local.username,
        margin: const EdgeInsets.only(bottom: 10),
        textInputAction: TextInputAction.next,
        controller: _usernameController,
        validator: (text) {
          final username = text ?? '';
          if (username.trim().isEmpty) {
            return local.emptyFieldValidation(local.username);
          }
          if (RegExp(r'[\w-]+').firstMatch(username)?[0] != username) {
            return local.invalidFieldValidation;
          }
          return null;
        },
      ),
      TextInput(
        label: local.password,
        margin: const EdgeInsets.only(bottom: 10),
        obscureText: true,
        textInputAction: _isLogin ? TextInputAction.done : TextInputAction.next,
        controller: _passwordController,
        onSubmitted: (_) => _submit(),
        validator: (text) {
          final password = text ?? '';
          if (password.trim().isEmpty) {
            return local.emptyFieldValidation(local.password);
          }
          if (password.length < 8) {
            return local.passwordInvalidLength(local.minimum, 8);
          }
          if (password.length > 12) {
            return local.passwordInvalidLength(local.maximum, 12);
          }
          return null;
        },
      ),
      if (_isSignup)
        TextInput(
          label: local.confirmPassword,
          margin: const EdgeInsets.only(bottom: 10),
          obscureText: true,
          textInputAction: TextInputAction.done,
          onSubmitted: (_) => _submit(),
          validator: (text) {
            final password = text ?? '';
            if (password != _passwordController.text) {
              return local.passwordsDoNotMatch;
            }
            return null;
          },
        ),
      _isLoading
          ? const SpinKitThreeBounce(
              size: 43,
              color: AppColors.secondary,
            )
          : LayoutBuilder(
              builder: (context, constraints) => Wrap(
                spacing: 10,
                children: [
                  SizedBox(
                    width: (constraints.maxWidth - 10) * .5,
                    child: AccentButton(
                      label: local.login,
                      onPressed: _isLogin ? _submit : _switchAuthMode,
                    ),
                  ),
                  SizedBox(
                    width: (constraints.maxWidth - 10) * .5,
                    child: AccentButton(
                      label: local.signup,
                      onPressed: _isSignup ? _submit : _switchAuthMode,
                    ),
                  ),
                ],
              ),
            )
    ];

    return Stack(
      alignment: Alignment.topCenter,
      clipBehavior: Clip.none,
      children: [
        Card(
          elevation: 10,
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
              key: _formKey,
              child: AnimatedList(
                key: _listKey,
                initialItemCount: 3,
                shrinkWrap: true,
                itemBuilder: (context, index, animation) {
                  return SizeTransition(
                    sizeFactor: CurvedAnimation(
                      curve: Curves.ease,
                      parent: animation,
                    ),
                    child: fields[index],
                  );
                },
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

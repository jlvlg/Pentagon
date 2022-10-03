import 'dart:io';

import 'package:flutter/material.dart';
import 'package:pentagon/controllers/post_controller.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/providers/post_provider.dart';
import 'package:pentagon/util/components/button.dart';
import 'package:pentagon/util/components/text_input.dart';
import 'package:pentagon/util/helpers/pick_image.dart';
import 'package:provider/provider.dart';

class CreatePostScreen extends StatefulWidget {
  const CreatePostScreen({super.key});

  @override
  State<CreatePostScreen> createState() => _CreatePostScreenState();
}

class _CreatePostScreenState extends State<CreatePostScreen> {
  final _formKey = GlobalKey<FormState>();
  String? title;
  String? body;
  File? image;

  Future<void> getImage() async {
    File? file = await PickImage.pickImage(context);
    if (file != null) {
      setState(() => image = file);
    }
  }

  Future<void> save() async {
    final provider = context.read<PostProvider>();
    if (_formKey.currentState?.validate() ?? false) {
      try {
        PostController.postPost(
          provider.authProfile,
          title!,
          body!,
          image: image,
          token: provider.token,
        );
        Navigator.of(context).pop();
      } catch (error) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text(error.toString()),
          ),
        );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.fromLTRB(
          20, 20, 20, 20 + MediaQuery.of(context).viewInsets.bottom),
      child: SingleChildScrollView(
        child: Form(
          key: _formKey,
          autovalidateMode: AutovalidateMode.onUserInteraction,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.end,
            children: [
              Row(
                children: [
                  GestureDetector(
                    onTap: image != null
                        ? () => setState(() => image = null)
                        : getImage,
                    child: Card(
                      elevation: 5,
                      clipBehavior: Clip.antiAlias,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: image != null
                          ? Image.file(
                              image!,
                              height: 100,
                              width: 100,
                            )
                          : Container(
                              color: Colors.grey,
                              height: 100,
                              width: 100,
                              child: const Icon(Icons.image, size: 50)),
                    ),
                  ),
                  Expanded(
                    child: TextInput(
                      label: S.of(context).title,
                      textInputAction: TextInputAction.next,
                      initialText: title,
                      onChanged: (value) => title = value,
                      validator: (value) {
                        String title = value ?? '';
                        if (title.isEmpty) {
                          return S
                              .of(context)
                              .emptyFieldValidation(S.of(context).title);
                        }
                        return null;
                      },
                    ),
                  ),
                ],
              ),
              TextInput(
                label: S.of(context).body,
                textInputAction: TextInputAction.done,
                onChanged: (value) => body = value,
                initialText: body,
                maxLength: 1500,
                validator: (value) {
                  String body = value ?? '';
                  if (body.isEmpty) {
                    return S
                        .of(context)
                        .emptyFieldValidation(S.of(context).body);
                  }
                  return null;
                },
                maxLines: 10,
              ),
              Button(
                label: 'Post',
                onPressed: save,
              ),
            ],
          ),
        ),
      ),
    );
  }
}

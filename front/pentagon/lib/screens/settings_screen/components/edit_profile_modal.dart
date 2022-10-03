import 'dart:io';
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/providers/profile_provider.dart';
import 'package:pentagon/util/components/button.dart';
import 'package:pentagon/util/components/profile_picture.dart';
import 'package:pentagon/util/helpers/pick_image.dart';
import 'package:provider/provider.dart';

class EditProfileModal extends StatefulWidget {
  const EditProfileModal({super.key});

  @override
  State<EditProfileModal> createState() => _EditProfileModalState();
}

class _EditProfileModalState extends State<EditProfileModal> {
  final _formKey = GlobalKey<FormState>();
  File? image;
  String? name;
  String? description;

  Future<void> getImage() async {
    File? file = await PickImage.pickImage(context);
    if (file != null) {
      setState(() => image = file);
    }
  }

  Future<void> save(Profile profile) async {
    if (_formKey.currentState?.validate() ?? false) {
      profile.name = name ?? profile.name;
      profile.description = description ?? profile.description;
      try {
        context.read<AuthProvider>().patchProfile(profile, image);
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
    final mediaQuery = MediaQuery.of(context);
    final size = mediaQuery.size;
    final isLandscape = mediaQuery.orientation == Orientation.landscape;
    final local = S.of(context);

    return Padding(
      padding: EdgeInsets.only(bottom: mediaQuery.viewInsets.bottom),
      child: Padding(
        padding: EdgeInsets.symmetric(
            horizontal: isLandscape ? size.width / 5 : 20, vertical: 20),
        child: Consumer<ProfileProvider>(
          builder: (context, provider, child) => SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    GestureDetector(
                      onTap: getImage,
                      child: Card(
                        child: ClipRRect(
                          borderRadius: BorderRadius.circular(5),
                          child: Stack(
                            children: [
                              ImageFiltered(
                                imageFilter: ImageFilter.blur(
                                  sigmaX: 5,
                                  sigmaY: 5,
                                ),
                                child: Stack(
                                  children: [
                                    image != null
                                        ? Image.file(
                                            image!,
                                            width: 100,
                                            height: 100,
                                          )
                                        : ProfilePicture(
                                            provider.authProfile,
                                            size: 100,
                                          ),
                                    Container(
                                      height: 100,
                                      width: 100,
                                      color: Colors.grey.withOpacity(.5),
                                    ),
                                  ],
                                ),
                              ),
                              const Icon(
                                Icons.edit,
                                size: 100,
                                color: Colors.white,
                              ),
                            ],
                          ),
                        ),
                      ),
                    ),
                    Expanded(
                      child: Padding(
                        padding: const EdgeInsets.symmetric(
                            horizontal: 5, vertical: 10),
                        child: Form(
                          key: _formKey,
                          child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                TextFormField(
                                  maxLength: 250,
                                  decoration: InputDecoration(
                                    labelText: local.name,
                                  ),
                                  controller: TextEditingController(
                                    text: name ?? provider.authProfile.name,
                                  ),
                                  autovalidateMode:
                                      AutovalidateMode.onUserInteraction,
                                  onChanged: (value) => name = value,
                                  validator: (value) {
                                    String name = value ?? '';
                                    if (name.isEmpty) {
                                      return local
                                          .emptyFieldValidation(local.name);
                                    }
                                    if (RegExp(r'[ A-Za-zÀ-ÖØ-öø-ÿ]+')
                                            .firstMatch(name)?[0] !=
                                        name) {
                                      return local.invalidFieldValidation;
                                    }
                                    return null;
                                  },
                                ),
                                TextField(
                                  maxLines: 3,
                                  decoration: InputDecoration(
                                    labelText: local.description,
                                  ),
                                  controller: TextEditingController(
                                    text: description ??
                                        provider.authProfile.description,
                                  ),
                                  onChanged: (value) =>
                                      provider.authProfile.description = value,
                                ),
                              ]),
                        ),
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 5),
                Row(
                  children: [
                    Expanded(
                      child: ElevatedButton(
                        onPressed: () {
                          setState(() {
                            name = null;
                            description = null;
                            image = null;
                          });
                          Navigator.of(context).pop();
                        },
                        style: ElevatedButton.styleFrom(
                          backgroundColor: Theme.of(context).errorColor,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(10),
                          ),
                        ),
                        child: const Padding(
                          padding: EdgeInsets.symmetric(vertical: 10),
                          child: Icon(Icons.arrow_back),
                        ),
                      ),
                    ),
                    const SizedBox(width: 5),
                    Expanded(
                      flex: 4,
                      child: Button(
                        label: local.save,
                        onPressed: () => save(provider.authProfile),
                      ),
                    ),
                  ],
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}

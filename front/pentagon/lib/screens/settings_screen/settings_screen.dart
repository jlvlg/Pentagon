import 'dart:async';

import 'package:flutter/material.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/providers/locale_provider.dart';
import 'package:pentagon/providers/profile_provider.dart';
import 'package:pentagon/screens/settings_screen/components/edit_profile_modal.dart';
import 'package:pentagon/util/components/button.dart';
import 'package:pentagon/util/components/profile_picture.dart';
import 'package:provider/provider.dart';

class SettingsScreen extends StatefulWidget {
  const SettingsScreen({super.key});

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
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
                Card(
                  elevation: 10,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.stretch,
                      children: [
                        Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Card(
                              child: ClipRRect(
                                borderRadius: BorderRadius.circular(5),
                                child: ProfilePicture(
                                  provider.authProfile,
                                  size: 100,
                                ),
                              ),
                            ),
                            Expanded(
                              child: Padding(
                                padding: const EdgeInsets.symmetric(
                                    horizontal: 5, vertical: 10),
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Text(
                                      '${local.name}: ${provider.authProfile.name}',
                                      style: const TextStyle(fontSize: 18),
                                    ),
                                    Text(
                                      '${local.description}: ${provider.authProfile.description ?? ''}',
                                      maxLines: 3,
                                      overflow: TextOverflow.ellipsis,
                                    ),
                                  ],
                                ),
                              ),
                            ),
                          ],
                        ),
                        const SizedBox(height: 5),
                        Button(
                          label: local.edit,
                          onPressed: () => showModalBottomSheet(
                              context: context,
                              builder: (context) => const EditProfileModal()),
                        ),
                        if (!isLandscape) const SizedBox(height: 10),
                        if (!isLandscape)
                          Button(
                            label: local.logout,
                            onPressed: () {
                              context.read<AuthProvider>().logout();
                              Navigator.of(context).pop();
                            },
                            color: Theme.of(context).errorColor,
                          ),
                        const SizedBox(height: 10),
                        Button(
                          label:
                              '${local.delete} ${local.account.toLowerCase()}',
                          onPressed: () {
                            showDialog(
                              context: context,
                              builder: (context) => AlertDialog(
                                title: Text(
                                  '${local.delete} ${local.account.toLowerCase()}',
                                ),
                                content: Text(local.deleteAccountMsg),
                                actions: [
                                  TextButton(
                                    child: Text(local.cancel),
                                    onPressed: () =>
                                        Navigator.of(context).pop(false),
                                  ),
                                  TextButton(
                                    onPressed: () =>
                                        Navigator.of(context).pop(true),
                                    style: TextButton.styleFrom(
                                      backgroundColor:
                                          Theme.of(context).errorColor,
                                      foregroundColor: Colors.white,
                                    ),
                                    child: Text(local.delete),
                                  ),
                                ],
                              ),
                            ).then((value) {
                              if (value) {
                                context.read<AuthProvider>().deleteAccount();
                                Navigator.of(context).pop();
                              }
                            });
                          },
                          color: Colors.black,
                          textColor: Colors.white,
                        ),
                      ],
                    ),
                  ),
                ),
                const SizedBox(height: 10),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Text(
                      local.language,
                      style: const TextStyle(
                        fontSize: 20,
                      ),
                    ),
                    DropdownButton(
                      value: context
                          .read<LocaleProvider>()
                          .locale
                          .toString()
                          .split('_')[0],
                      items: const [
                        DropdownMenuItem(
                          value: 'en',
                          child: Text('English'),
                        ),
                        DropdownMenuItem(
                          value: 'pt',
                          child: Text('Português'),
                        ),
                      ],
                      onChanged: (String? locale) {
                        context.read<LocaleProvider>().setLocale = locale!;
                      },
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

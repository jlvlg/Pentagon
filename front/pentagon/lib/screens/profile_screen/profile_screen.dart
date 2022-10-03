import 'package:flutter/material.dart';
import 'package:pentagon/controllers/user_controller.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/providers/profile_provider.dart';
import 'package:pentagon/screens/profile_screen/components/profile_details.dart';
import 'package:pentagon/util/components/app_layout.dart';
import 'package:pentagon/util/components/ensure_online.dart';
import 'package:pentagon/util/constants/app_routes.dart';
import 'package:provider/provider.dart';

class ProfileScreen extends StatelessWidget {
  final Profile profile;

  const ProfileScreen(this.profile, {super.key});

  @override
  Widget build(BuildContext context) {
    final provider = context.read<ProfileProvider>();

    return EnsureOnline(
      AppLayout(
        floatingActionButton: provider.profile != provider.authProfile
            ? FloatingActionButton(
                heroTag: 'homefab',
                onPressed: () {
                  UserController.switchFollowUser(
                          provider.authProfile.user.id, profile.user.id,
                          token: provider.token)
                      .then(
                    (value) =>
                        provider.loadProfile(id: profile.id).whenComplete(
                              () => ScaffoldMessenger.of(context).showSnackBar(
                                SnackBar(
                                  duration: const Duration(seconds: 1),
                                  content: Text(
                                    value == null
                                        ? S
                                            .of(context)
                                            .onError(S.of(context).unexpected)
                                        : value == 0
                                            ? 'Followed'
                                            : 'Unfollowed',
                                  ),
                                ),
                              ),
                            ),
                  );
                },
                child: const Icon(
                  Icons.group_add,
                  color: Colors.white,
                ),
              )
            : null,
        floatingActionButtonLocation:
            MediaQuery.of(context).orientation == Orientation.landscape
                ? FloatingActionButtonLocation.startFloat
                : FloatingActionButtonLocation.centerDocked,
        onTap: (index) => Navigator.of(context).pushNamedAndRemoveUntil(
          AppRoutes.home,
          (_) => false,
          arguments: index,
        ),
        child: ProfileDetails(profile),
      ),
    );
  }
}

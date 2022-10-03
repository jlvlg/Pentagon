import 'package:flutter/material.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/providers/profile_provider.dart';
import 'package:pentagon/screens/feed_screen/feed_screen.dart';
import 'package:pentagon/screens/profile_screen/components/profile_description.dart';
import 'package:provider/provider.dart';

class ProfileDetails extends StatelessWidget {
  final Profile profile;

  const ProfileDetails(this.profile, {super.key});

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);
    final isLandscape = mediaQuery.orientation == Orientation.landscape;
    final provider = context.read<ProfileProvider>();

    return FutureBuilder(
      future: provider.loadProfile(id: profile.id),
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return const Center(child: CircularProgressIndicator());
        }
        return Container(
          child: isLandscape
              ? Row(
                  children: [
                    Expanded(
                      child: ProfileDescription(profile),
                    ),
                    Expanded(
                      flex: 2,
                      child: FeedScreen(profile: profile),
                    ),
                  ],
                )
              : Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    ProfileDescription(profile),
                    Expanded(child: FeedScreen(profile: profile)),
                  ],
                ),
        );
      },
    );
  }
}

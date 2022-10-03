import 'package:flutter/material.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/util/components/profile_picture.dart';
import 'package:pentagon/util/constants/app_routes.dart';

class ProfileTile extends StatelessWidget {
  final Profile profile;

  const ProfileTile(this.profile, {super.key});

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 5,
      shape: RoundedRectangleBorder(
          side: const BorderSide(color: Colors.grey, width: 0.5),
          borderRadius: BorderRadius.circular(10)),
      child: InkWell(
        onTap: () => Navigator.of(context)
            .pushNamed(AppRoutes.profile, arguments: profile),
        child: Row(
          children: [
            Card(
              margin: const EdgeInsets.all(8),
              clipBehavior: Clip.antiAlias,
              shape: const CircleBorder(),
              child: ProfilePicture(profile),
            ),
            Expanded(
              child: Padding(
                padding: const EdgeInsets.only(left: 8),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      profile.name,
                      style: const TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 16,
                      ),
                      overflow: TextOverflow.ellipsis,
                    ),
                    Text(
                      '@${profile.user.auth.username}',
                      style: const TextStyle(
                        fontSize: 12,
                        fontWeight: FontWeight.w300,
                      ),
                      overflow: TextOverflow.ellipsis,
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

import 'package:flutter/material.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/screens/profile_screen/components/profile_score.dart';
import 'package:pentagon/util/components/profile_picture.dart';
import 'package:pentagon/util/constants/app_colors.dart';

class ProfileDescription extends StatefulWidget {
  final Profile profile;

  const ProfileDescription(this.profile, {super.key});

  @override
  State<ProfileDescription> createState() => _ProfileDescriptionState();
}

class _ProfileDescriptionState extends State<ProfileDescription> {
  bool _isNameExpanded = false;
  bool _isDescriptionExpanded = false;

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);
    final size = mediaQuery.size;
    final isLandscape = mediaQuery.orientation == Orientation.landscape;

    return Column(
      crossAxisAlignment:
          isLandscape ? CrossAxisAlignment.start : CrossAxisAlignment.center,
      children: [
        Stack(
          clipBehavior: Clip.none,
          alignment:
              isLandscape ? Alignment.bottomLeft : Alignment.bottomCenter,
          children: [
            Container(
              width: size.width,
              height: size.height / 3,
              color: AppColors.primary,
            ),
            Positioned(
              bottom: -1,
              child: Container(
                decoration: BoxDecoration(
                  color: Theme.of(context).canvasColor,
                  borderRadius: isLandscape
                      ? const BorderRadius.only(topRight: Radius.circular(10))
                      : const BorderRadius.vertical(top: Radius.circular(10)),
                ),
                child: Padding(
                  padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(5),
                    child: ProfilePicture(
                      widget.profile,
                      size: size.height / 3 - 59,
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
        Expanded(
          flex: isLandscape ? 1 : -1,
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Row(
              children: [
                if (!isLandscape)
                  SizedBox(
                    height: 150,
                    width: 150,
                    child: ProfileScore(widget.profile),
                  ),
                Expanded(
                  flex: 2,
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              _isNameExpanded = !_isNameExpanded;
                            });
                          },
                          child: Text(
                            widget.profile.name,
                            maxLines: _isNameExpanded ? 8 : 1,
                            overflow: TextOverflow.ellipsis,
                            style: const TextStyle(
                              fontSize: 25,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        const SizedBox(height: 5),
                        Text(
                          '@${widget.profile.user.auth.username}',
                          style: const TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.w300,
                          ),
                        ),
                        const SizedBox(width: 30),
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              _isDescriptionExpanded = !_isDescriptionExpanded;
                            });
                          },
                          child: Text(
                            widget.profile.description ?? '',
                            maxLines: _isDescriptionExpanded ? 10 : 3,
                            overflow: TextOverflow.ellipsis,
                            style: const TextStyle(
                              fontSize: 18,
                            ),
                          ),
                        ),
                        const SizedBox(height: 10),
                        if (isLandscape)
                          Expanded(child: ProfileScore(widget.profile)),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ],
    );
  }
}

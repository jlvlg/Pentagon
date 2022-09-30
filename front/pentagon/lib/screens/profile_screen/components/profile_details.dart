import 'package:flutter/material.dart';
import 'package:pentagon/providers/profile_provider.dart';
import 'package:pentagon/screens/profile_screen/components/profile_description.dart';
import 'package:pentagon/util/components/profile_picture.dart';
import 'package:pentagon/util/constants/app_colors.dart';
import 'package:provider/provider.dart';

class ProfileDetails extends StatefulWidget {
  final String userId;

  const ProfileDetails(this.userId, {super.key});

  @override
  State<ProfileDetails> createState() => _ProfileDetailsState();
}

class _ProfileDetailsState extends State<ProfileDetails> {
  late bool isLoading;
  bool isEditing = false;

  @override
  void initState() {
    super.initState();
    isLoading = true;
    context
        .read<ProfileProvider>()
        .loadProfile(id: widget.userId)
        .whenComplete(() => setState(() => isLoading = false));
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);
    final size = mediaQuery.size;
    final isLandscape = mediaQuery.orientation == Orientation.landscape;

    return isLoading
        ? Center(child: CircularProgressIndicator())
        : RefreshIndicator(
            onRefresh: () =>
                context.read<ProfileProvider>().loadProfile(id: widget.userId),
            child: Consumer<ProfileProvider>(
              builder: (context, value, child) => ListView(
                padding: EdgeInsets.zero,
                children: [
                  Stack(
                    clipBehavior: Clip.none,
                    alignment: isLandscape
                        ? Alignment.bottomLeft
                        : Alignment.bottomCenter,
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
                                ? const BorderRadius.only(
                                    topRight: Radius.circular(10))
                                : const BorderRadius.vertical(
                                    top: Radius.circular(10)),
                          ),
                          child: Padding(
                            padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
                            child: ClipRRect(
                              borderRadius: BorderRadius.circular(5),
                              child: Stack(
                                alignment: Alignment.topRight,
                                children: [
                                  ProfilePicture(
                                    value.profile!,
                                    size: size.height / 4,
                                  ),
                                  if (isEditing)
                                    IconButton(
                                      onPressed: () {},
                                      icon: Icon(
                                        Icons.edit,
                                        color: Theme.of(context).canvasColor,
                                      ),
                                    )
                                ],
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                  ProfileDescription(value.profile!),
                ],
              ),
            ),
          );
  }
}

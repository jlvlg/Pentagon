import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:pentagon/models/profile.dart';

class ProfilePicture extends StatelessWidget {
  final Profile profile;
  final double size;

  const ProfilePicture(this.profile, {this.size = 50, super.key});

  @override
  Widget build(BuildContext context) {
    return CachedNetworkImage(
      width: size,
      height: size,
      imageUrl: profile.image ?? '',
      placeholder: (context, url) => const CircularProgressIndicator(),
      errorWidget: (context, url, error) =>
          SvgPicture.asset('assets/images/default_profile_picture.svg'),
    );
  }
}

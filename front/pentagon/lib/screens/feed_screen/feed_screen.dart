import 'package:flutter/material.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/providers/post_provider.dart';
import 'package:pentagon/screens/feed_screen/components/post_tile.dart';
import 'package:provider/provider.dart';

class FeedScreen extends StatelessWidget {
  final Profile? profile;

  const FeedScreen({this.profile, super.key});

  @override
  Widget build(BuildContext context) {
    final provider = context.read<PostProvider>();
    return FutureBuilder(
      future: profile != null
          ? provider.getPosts(profile!)
          : provider.getFollowing(),
      builder: ((context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return const Center(
            child: CircularProgressIndicator(),
          );
        }
        return RefreshIndicator(
          onRefresh: () => profile != null
              ? provider.getPosts(profile!)
              : provider.getFollowing(),
          child: ListView.builder(
            padding:
                EdgeInsets.only(bottom: MediaQuery.of(context).padding.bottom),
            itemCount: provider.itemCount,
            itemBuilder: (context, index) => PostTile(
              provider.posts[index],
            ),
          ),
        );
      }),
    );
  }
}

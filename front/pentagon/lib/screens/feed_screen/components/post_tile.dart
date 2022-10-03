import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:pentagon/models/post.dart';

class PostTile extends StatefulWidget {
  final Post post;

  const PostTile(this.post, {super.key});

  @override
  State<PostTile> createState() => _PostTileState();
}

class _PostTileState extends State<PostTile> {
  bool _isExpanded = false;

  @override
  Widget build(BuildContext context) {
    return Card(
        elevation: 10,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
        clipBehavior: Clip.antiAlias,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            SizedBox(
              width: double.infinity,
              child: CachedNetworkImage(
                fit: BoxFit.cover,
                imageUrl: widget.post.image ?? '',
                placeholder: (context, placeholder) =>
                    const Center(child: CircularProgressIndicator()),
                errorWidget: (context, url, error) => const SizedBox(),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(10),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Expanded(
                    child: GestureDetector(
                      onTap: () => setState(() => _isExpanded = !_isExpanded),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text('@${widget.post.author.auth.username}'),
                          const SizedBox(height: 5),
                          Text(
                            widget.post.title,
                            style: const TextStyle(
                              fontSize: 20,
                              fontWeight: FontWeight.bold,
                            ),
                            maxLines: _isExpanded ? null : 2,
                            overflow: TextOverflow.ellipsis,
                          ),
                          Text(
                            widget.post.text,
                            maxLines: _isExpanded ? null : 2,
                            overflow: TextOverflow.ellipsis,
                          ),
                        ],
                      ),
                    ),
                  ),
                  Column(
                    children: [
                      IconButton(onPressed: () {}, icon: const Icon(Icons.thumb_up)),
                      IconButton(
                          onPressed: () {}, icon: const Icon(Icons.thumb_down)),
                    ],
                  ),
                ],
              ),
            )
          ],
        ));
  }
}

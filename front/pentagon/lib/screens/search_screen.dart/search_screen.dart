import 'package:flutter/material.dart';
import 'package:pentagon/exceptions/http_exception.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/providers/profile_provider.dart';
import 'package:pentagon/screens/search_screen.dart/components/profile_tile.dart';
import 'package:pentagon/util/components/app_layout.dart';
import 'package:pentagon/util/components/search_field.dart';
import 'package:pentagon/util/constants/app_routes.dart';
import 'package:provider/provider.dart';

class SearchScreen extends StatefulWidget {
  final text;

  const SearchScreen(this.text, {super.key});

  @override
  State<SearchScreen> createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  @override
  void initState() {
    super.initState();
    search(widget.text);
  }

  void showSnackbar(String msg) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(msg)),
    );
  }

  Future<void> search(String text) async {
    final profileProvider = context.read<ProfileProvider>();

    try {
      await profileProvider.search(text);
    } on HttpException catch (error) {
      showSnackbar(error.toString());
    } catch (error) {
      showSnackbar(S.of(context).onError(S.of(context).unexpected));
    }
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);
    final isLandscape = mediaQuery.orientation == Orientation.landscape;

    return AppLayout(
      floatingActionButton: SizedBox(
        height: 0,
        width: 0,
        child: FloatingActionButton(
          heroTag: 'homefab',
          onPressed: () {},
          child: const Icon(Icons.add, size: 0),
        ),
      ),
      floatingActionButtonLocation: isLandscape
          ? FloatingActionButtonLocation.endFloat
          : FloatingActionButtonLocation.centerFloat,
      onTap: (index) => Navigator.of(context)
          .pushReplacementNamed(AppRoutes.home, arguments: index),
      child: Consumer<ProfileProvider>(
        builder: (context, value, child) => Padding(
          padding: const EdgeInsets.all(5),
          child: Column(
            children: [
              SearchField(
                onSubmitted: (text) => search(text),
              ),
              Expanded(
                child: ListView.builder(
                  itemCount: value.itemCount,
                  itemBuilder: (context, index) => ProfileTile(
                    value.profiles[index],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

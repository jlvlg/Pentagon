import 'package:flutter/material.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/screens/profile_screen/components/profile_details.dart';
import 'package:pentagon/screens/settings_screen/settings_screen.dart';
import 'package:pentagon/screens/create_post_screen/create_post_screen.dart';
import 'package:pentagon/screens/feed_screen/feed_screen.dart';
import 'package:pentagon/util/components/app_layout.dart';
import 'package:pentagon/util/components/search_field.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatefulWidget {
  final int initScreen;

  const HomeScreen({this.initScreen = 0, super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late final PageController _pageController;
  late int _currentPage;

  @override
  void initState() {
    super.initState();
    _currentPage = widget.initScreen;
    _pageController = PageController(initialPage: _currentPage);
  }

  @override
  void dispose() {
    super.dispose();
    _pageController.dispose();
  }

  void changePage(int page) {
    _pageController.animateToPage(
      page,
      duration: const Duration(milliseconds: 200),
      curve: Curves.ease,
    );
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);
    final isLandscape = mediaQuery.orientation == Orientation.landscape;

    return AppLayout(
      onTap: changePage,
      currentIndex: _currentPage,
      floatingActionButton: SizedBox(
        height: 50,
        width: 50,
        child: [
          FloatingActionButton(
            elevation: 10,
            onPressed: () => showModalBottomSheet(
                context: context,
                builder: (context) => const CreatePostScreen()),
            heroTag: 'homefab',
            child: const Icon(
              Icons.add,
              color: Colors.white,
            ),
          ),
          isLandscape
              ? null
              : FloatingActionButton(
                  elevation: 10,
                  onPressed: () => showModalBottomSheet(
                      context: context,
                      builder: (context) => const SettingsScreen()),
                  heroTag: 'homefab',
                  child: const Icon(
                    Icons.settings,
                    color: Colors.white,
                  ),
                ),
          null,
        ][_currentPage],
      ),
      child: Stack(
        children: [
          Consumer<AuthProvider>(
            builder: (context, value, child) => PageView(
              physics: const NeverScrollableScrollPhysics(),
              controller: _pageController,
              scrollDirection: isLandscape ? Axis.vertical : Axis.horizontal,
              onPageChanged: (value) => setState(() => _currentPage = value),
              children: [
                Padding(
                  padding: const EdgeInsets.all(5),
                  child: Column(
                    children: const [
                      SearchField(),
                      Expanded(
                        child: FeedScreen(),
                      ),
                    ],
                  ),
                ),
                ProfileDetails(value.authProfile!),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

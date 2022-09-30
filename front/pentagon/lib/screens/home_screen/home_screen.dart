import 'package:flutter/material.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/screens/profile_screen/components/profile_details.dart';
import 'package:pentagon/screens/settings_screen/settings_screen.dart';
import 'package:pentagon/screens/create_post_screen/create_post_screen.dart';
import 'package:pentagon/screens/feed_screen/feed_screen.dart';
import 'package:pentagon/util/components/app_layout.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatefulWidget {
  final int initScreen;

  const HomeScreen({this.initScreen = 0, super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late final PageController _pageController;
  bool _isTrayOpen = false;
  bool _trayShadow = false;
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

  void switchTray() {
    setState(() {
      _isTrayOpen = !_isTrayOpen;
      _trayShadow = false;
    });
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
    final size = mediaQuery.size;

    return AppLayout(
      onTap: changePage,
      currentIndex: _currentPage,
      floatingActionButton: SizedBox(
        height: 50,
        width: 50,
        child: FloatingActionButton(
          elevation: 10,
          onPressed: switchTray,
          heroTag: 'homefab',
          child: IconTheme(
            data: const IconThemeData(color: Colors.white),
            child: AnimatedRotation(
              turns: _isTrayOpen ? 1 / 8 : 0,
              duration: const Duration(milliseconds: 200),
              child: const Icon(Icons.add),
            ),
          ),
        ),
      ),
      child: Stack(
        children: [
          PageView(
            physics: const NeverScrollableScrollPhysics(),
            controller: _pageController,
            scrollDirection: isLandscape ? Axis.vertical : Axis.horizontal,
            onPageChanged: (value) => setState(() => _currentPage = value),
            children: [
              const FeedScreen(),
              Consumer<AuthProvider>(
                builder: (context, value, child) => ProfileDetails(value.id!),
              ),
              const SettingsScreen(),
            ],
          ),
          AnimatedPositioned(
            left: 0,
            right: 0,
            top: _isTrayOpen ? 0 : size.height,
            bottom: _isTrayOpen ? 0 : -size.height,
            duration: const Duration(milliseconds: 300),
            child: CreatePostScreen(
              shadow: _trayShadow,
            ),
            onEnd: () => setState(() => _trayShadow = _isTrayOpen && true),
          ),
        ],
      ),
    );
  }
}

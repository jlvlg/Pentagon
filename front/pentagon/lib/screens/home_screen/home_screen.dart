import 'dart:math';

import 'package:flutter/material.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/screens/feed_screen/feed_screen.dart';
import 'package:pentagon/screens/profile_screen/profile_screen.dart';
import 'package:pentagon/util/components/custom_navigation_bar.dart';
import 'package:pentagon/util/components/custom_navigation_bar_item.dart';
import 'package:pentagon/util/constants/app_routes.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final _pageController = PageController();
  bool _isTrayOpened = false;
  int _currentPage = 0;

  void sitchTray() {
    setState(() {
      _isTrayOpened = !_isTrayOpened;
    });
  }

  @override
  Widget build(BuildContext context) {
    final isLandscape =
        MediaQuery.of(context).orientation == Orientation.landscape;
    return Scaffold(
      body: PageView(
        physics: NeverScrollableScrollPhysics(),
        controller: _pageController,
        scrollDirection: isLandscape ? Axis.vertical : Axis.horizontal,
        onPageChanged: (value) => setState(() => _currentPage = value),
        children: [
          FeedScreen(),
          ProfileScreen(),
        ],
      ),
      bottomNavigationBar: CustomNavigationBar(
        selectedItem: _currentPage,
        onTap: (value) => _pageController.animateToPage(
          value,
          duration: Duration(milliseconds: 200),
          curve: Curves.ease,
        ),
        onTapSpecialItem: sitchTray,
        items: const [
          CustomNavigationBarItem(
            icon: Icon(Icons.home_outlined),
            activeIcon: Icon(Icons.home),
          ),
          CustomNavigationBarItem(
            icon: Icon(Icons.pentagon_outlined),
            activeIcon: Icon(Icons.pentagon),
          ),
        ],
        specialItem: CustomNavigationBarItem(
          icon: AnimatedRotation(
            turns: _isTrayOpened ? 1 / 8 : 0,
            duration: Duration(milliseconds: 100),
            child: Icon(Icons.add),
          ),
        ),
      ),
    );
  }
}

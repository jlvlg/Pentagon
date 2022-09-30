import 'package:flutter/material.dart';
import 'package:pentagon/generated/l10n.dart';
import 'package:pentagon/providers/auth_provider.dart';
import 'package:pentagon/util/components/custom_navigation_bar.dart';
import 'package:pentagon/util/components/custom_navigation_bar_item.dart';
import 'package:pentagon/util/components/lateral_bar.dart';
import 'package:pentagon/util/components/lateral_bar_item.dart';
import 'package:pentagon/util/constants/app_routes.dart';
import 'package:provider/provider.dart';

class AppLayout extends StatelessWidget {
  final Widget? floatingActionButton;
  final Widget child;
  final Function(int) onTap;
  final int currentIndex;
  final FloatingActionButtonLocation? floatingActionButtonLocation;

  const AppLayout({
    this.floatingActionButton,
    this.floatingActionButtonLocation,
    required this.child,
    required this.onTap,
    this.currentIndex = 0,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);
    final isLandscape = mediaQuery.orientation == Orientation.landscape;
    return Scaffold(
      floatingActionButton: floatingActionButton,
      floatingActionButtonLocation: floatingActionButtonLocation ??
          (isLandscape
              ? FloatingActionButtonLocation.endFloat
              : FloatingActionButtonLocation.centerDocked),
      body: Padding(
        padding: mediaQuery.padding,
        child: Row(
          children: [
            if (isLandscape)
              Expanded(
                child: LateralBar(
                  selectedItem: currentIndex,
                  onTap: onTap,
                  items: [
                    LateralBarItem(
                      icon: const Icon(Icons.home_outlined),
                      activeIcon: const Icon(Icons.home),
                      label: S.of(context).home,
                    ),
                    LateralBarItem(
                      icon: const Icon(Icons.pentagon_outlined),
                      activeIcon: const Icon(Icons.pentagon),
                      label: S.of(context).profile,
                    ),
                    LateralBarItem(
                      icon: const Icon(Icons.settings_outlined),
                      activeIcon: const Icon(Icons.settings),
                      label: S.of(context).settings,
                    ),
                    LateralBarItem(
                        icon: const Icon(Icons.logout),
                        label: S.of(context).logout,
                        onTap: () {
                          Navigator.of(context).popUntil(
                              (route) => route.settings.name == AppRoutes.home);
                          Provider.of<AuthProvider>(context, listen: false)
                              .logout();
                        })
                  ],
                ),
              ),
            const VerticalDivider(width: 0),
            Expanded(flex: 4, child: child)
          ],
        ),
      ),
      extendBody: true,
      bottomNavigationBar: isLandscape
          ? null
          : Container(
              decoration: const BoxDecoration(
                boxShadow: [
                  BoxShadow(spreadRadius: -15, blurRadius: 50),
                ],
              ),
              child: BottomAppBar(
                notchMargin: 8,
                shape: const CircularNotchedRectangle(),
                child: CustomNavigationBar(
                  selectedItem: currentIndex,
                  onTap: onTap,
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
                ),
              ),
            ),
    );
  }
}

import 'package:flutter/material.dart';
import 'package:pentagon/screens/home_screen/home_screen.dart';
import 'package:pentagon/screens/profile_screen/profile_screen.dart';

class TabScreen extends StatelessWidget {
  const TabScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 2,
      child: Scaffold(
        bottomNavigationBar: Container(
          color: Theme.of(context).colorScheme.primary,
          child: const TabBar(
            indicatorColor: Colors.white,
            tabs: [
              Padding(
                padding: EdgeInsets.symmetric(vertical: 5),
                child: Tab(
                  icon: Icon(
                    Icons.home,
                    size: 40,
                  ),
                ),
              ),
              Padding(
                padding: EdgeInsets.symmetric(vertical: 5),
                child: Tab(
                  icon: Icon(
                    Icons.pentagon,
                    size: 40,
                  ),
                ),
              )
            ],
          ),
        ),
        body: const TabBarView(children: [HomeScreen(), ProfileScreen()]),
      ),
    );
  }
}

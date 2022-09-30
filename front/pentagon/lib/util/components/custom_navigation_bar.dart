import 'package:flutter/material.dart';
import 'package:pentagon/util/components/custom_navigation_bar_item.dart';

class CustomNavigationBar extends StatelessWidget {
  final List<CustomNavigationBarItem> items;
  final CustomNavigationBarItem? specialItem;
  final int selectedItem;
  final Function(int value) onTap;
  final Function(int value)? onActiveTap;

  const CustomNavigationBar({
    required this.items,
    required this.onTap,
    this.selectedItem = 0,
    this.onActiveTap,
    this.specialItem,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return SizedBox(
      height: size.height / 15,
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: items
            .asMap()
            .entries
            .map(
              (e) => Expanded(
                child: CustomNavigationBarItem(
                  icon: e.value.icon,
                  activeIcon: e.value.activeIcon,
                  isActive: selectedItem == e.key,
                  size: size.height / 20,
                  onTap: () {
                    if (selectedItem == e.key && onActiveTap != null) {
                      onActiveTap!(e.key);
                    } else {
                      onTap(e.key);
                    }
                  },
                ),
              ),
            )
            .toList(),
      ),
    );
  }
}

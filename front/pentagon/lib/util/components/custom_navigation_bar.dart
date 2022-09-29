import 'package:flutter/material.dart';
import 'package:pentagon/util/components/custom_navigation_bar_item.dart';

class CustomNavigationBar extends StatelessWidget {
  final List<CustomNavigationBarItem> items;
  final CustomNavigationBarItem? specialItem;
  final int selectedItem;
  final Function(int value) onTap;
  final Function(int value)? onActiveTap;
  final Function()? onTapSpecialItem;

  const CustomNavigationBar({
    required this.items,
    required this.onTap,
    this.selectedItem = 0,
    this.onActiveTap,
    this.specialItem,
    this.onTapSpecialItem,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: Theme.of(context).canvasColor,
        boxShadow: const [
          BoxShadow(
            blurRadius: 30,
            spreadRadius: -10,
          ),
        ],
      ),
      child: Stack(
        clipBehavior: Clip.none,
        children: [
          Row(
            children: items
                .asMap()
                .entries
                .map(
                  (e) => Expanded(
                    child: CustomNavigationBarItem(
                      icon: e.value.icon,
                      activeIcon: e.value.activeIcon,
                      isActive: selectedItem == e.key,
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
          if (specialItem != null)
            Positioned(
              top: -17,
              right: 0,
              left: 0,
              child: Card(
                  elevation: 10,
                  shape: CircleBorder(),
                  color: Theme.of(context).colorScheme.secondary,
                  child: Padding(
                    padding: EdgeInsets.all(2),
                    child: CustomNavigationBarItem(
                      icon: specialItem!.icon,
                      color: Colors.white,
                      onTap: onTapSpecialItem,
                    ),
                  )),
            ),
        ],
      ),
    );
  }
}

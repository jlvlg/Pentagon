import 'package:flutter/material.dart';
import 'package:pentagon/util/components/lateral_bar_item.dart';

class LateralBar extends StatelessWidget {
  final List<LateralBarItem> items;
  final int selectedItem;
  final Function(int value) onTap;

  const LateralBar({
    required this.items,
    required this.onTap,
    this.selectedItem = 0,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        AnimatedPositioned(
          top: selectedItem * 50,
          left: 0,
          right: 0,
          duration: const Duration(milliseconds: 100),
          child: Container(
              height: 50, color: Theme.of(context).colorScheme.primary),
        ),
        ListView.builder(
          padding: EdgeInsets.zero,
          itemCount: items.length,
          itemBuilder: (context, index) {
            return LateralBarItem(
              icon: items[index].icon,
              label: items[index].label,
              activeIcon: items[index].activeIcon,
              isActive: selectedItem == index,
              onTap: items[index].onTap ?? () => onTap(index),
            );
          },
        ),
      ],
    );
  }
}

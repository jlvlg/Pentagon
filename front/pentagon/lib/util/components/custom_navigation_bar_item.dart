import 'package:flutter/material.dart';

class CustomNavigationBarItem extends StatelessWidget {
  final Widget icon;
  final Widget? activeIcon;
  final bool isActive;
  final Color? color;
  final double? size;
  final Function()? onTap;

  const CustomNavigationBarItem({
    required this.icon,
    this.onTap,
    this.activeIcon,
    this.color,
    this.size,
    this.isActive = false,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      behavior: HitTestBehavior.translucent,
      onTap: onTap,
      child: IconTheme(
        data: IconThemeData(
          color: color ?? Theme.of(context).colorScheme.primary,
          size: size,
          // size: double.infinity,
        ),
        child: isActive ? activeIcon ?? icon : icon,
      ),
    );
  }
}

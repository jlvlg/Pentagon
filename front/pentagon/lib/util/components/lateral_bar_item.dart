import 'package:flutter/material.dart';

class LateralBarItem extends StatelessWidget {
  final Widget icon;
  final String label;
  final Widget? activeIcon;
  final bool isActive;
  final Function()? onTap;

  const LateralBarItem({
    required this.icon,
    required this.label,
    this.activeIcon,
    this.isActive = false,
    this.onTap,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        GestureDetector(
          behavior: HitTestBehavior.translucent,
          onTap: onTap,
          child: Container(
            height: 50,
            padding: const EdgeInsets.all(10),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                IconTheme(
                  data: IconThemeData(
                    color: isActive
                        ? Theme.of(context).canvasColor
                        : Theme.of(context).colorScheme.primary,
                  ),
                  child: isActive ? activeIcon ?? icon : icon,
                ),
                const SizedBox(width: 10),
                Text(
                  label,
                  style: TextStyle(
                    color: isActive
                        ? Theme.of(context).canvasColor
                        : Theme.of(context).colorScheme.primary,
                  ),
                ),
              ],
            ),
          ),
        ),
        const Divider(height: 0),
      ],
    );
  }
}

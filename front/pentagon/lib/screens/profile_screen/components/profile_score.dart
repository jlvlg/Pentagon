import 'package:fl_chart/fl_chart.dart';
import 'package:flutter/material.dart';
import 'package:pentagon/models/profile.dart';
import 'package:pentagon/generated/l10n.dart';

class ProfileScore extends StatelessWidget {
  final Profile profile;

  const ProfileScore(this.profile, {super.key});

  @override
  Widget build(BuildContext context) {
    final titles = [
      S.of(context).appearance,
      S.of(context).intelligence,
      S.of(context).character,
      S.of(context).humor,
      S.of(context).responsibility,
    ];

    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 5, horizontal: 15),
      child: RadarChart(
        RadarChartData(
          radarShape: RadarShape.polygon,
          ticksTextStyle: const TextStyle(color: Colors.transparent),
          tickBorderData: const BorderSide(width: 1),
          gridBorderData: const BorderSide(width: 1),
          dataSets: [
            RadarDataSet(
              entryRadius: 0.0,
              fillColor: Theme.of(context).colorScheme.primary.withOpacity(.5),
              borderColor: Theme.of(context).colorScheme.primary,
              dataEntries:
                  profile.score.map((e) => RadarEntry(value: e)).toList(),
            ),
            RadarDataSet(
              fillColor: Colors.transparent,
              borderColor: Colors.transparent,
              dataEntries: [10.0, 10.0, 10.0, 10.0, 10.0]
                  .map((e) => RadarEntry(value: e))
                  .toList(),
            ),
          ],
          titleTextStyle: const TextStyle(fontSize: 12),
          getTitle: (index, angle) => titles
              .map((e) => RadarChartTitle(
                    text: e,
                    angle: angle,
                  ))
              .toList()[index],
        ),
      ),
    );
  }
}

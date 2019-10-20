package ua.nulp.view;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class DiagramPanel extends XChartPanel<Chart> {
    DiagramPanel(int width, int height, List<String> xData, List<Integer> yData) {
        super(setCategoryChart(width, height, xData, yData));
    }

    DiagramPanel(int width, int height, Map<String, List<Integer>> data, int limit, String... seriesNames) {
        super(setXYChart(width, height, data, limit, seriesNames));
    }

    private static CategoryChart setCategoryChart(int width, int height, List<String> xData, List<Integer> yData) {
        CategoryChart chart = createXYChart(width, height, false);
        chart.addSeries("Series", xData, yData);
        return chart;
    }

    private static CategoryChart setXYChart(int width, int height, Map<String, List<Integer>> data, int limit,
                                            String... seriesNames) {
        CategoryChart chart = createXYChart(width, height, true);
        fillXYChart(chart, data, limit, seriesNames);
        return chart;
    }

    private static void fillXYChart(CategoryChart chart, Map<String, List<Integer>> data, int limit, String... seriesNames) {
        List<String> keys = new ArrayList<>(data.keySet());
        int size = data.entrySet().iterator().next().getValue().size();
        for (int i = 0; i < size; i++) {
            LinkedList<Integer> entries = new LinkedList<>();
            int currentIndex = i;
            List<String> resultKeys = keys.stream()
                    .sorted((o1, o2) -> {
                        if (o1.length() == 1 && o1.length() == o2.length()) {
                            return o1.compareToIgnoreCase(o2);
                        } else {
                            Integer o1EntriesNumber = data.get(o1).get(currentIndex);
                            Integer o2EntriesNumber = data.get(o2).get(currentIndex);
                            return o1EntriesNumber.compareTo(o2EntriesNumber);
                        }
                    })
                    .limit(limit)
                    .collect(Collectors.toList());
            resultKeys.forEach(key -> entries.add(data.get(key).get(currentIndex)));
            String seriesName;
            if (i < seriesNames.length) {
                seriesName = seriesNames[i];
            } else {
                seriesName = "Serie #" + (i + 1);
            }
            chart.addSeries(seriesName,
                    resultKeys,
                    entries);
        }
    }

    private static CategoryChart createXYChart(int width, int height, boolean overlapped) {
        CategoryChart chart = new CategoryChartBuilder()
                .width(width)
                .height(height)
                .theme(Styler.ChartTheme.XChart)
                .build();
        chart.getStyler().setLegendVisible(overlapped);
        chart.getStyler().setAxisTicksLineVisible(false);
        chart.getStyler().setDecimalPattern("#");
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setOverlapped(overlapped);
        return chart;
    }


}

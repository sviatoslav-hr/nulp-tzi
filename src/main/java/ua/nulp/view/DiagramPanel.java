package ua.nulp.view;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;

import java.util.List;

public class DiagramPanel extends XChartPanel<CategoryChart> {
    DiagramPanel(int width, int height, List<String> xData, List<Integer> yData) {
        super(setChart(width, height, xData, yData));
    }

    private static CategoryChart setChart(int width, int height, List<String> xData, List<Integer> yData) {
        CategoryChart chart = new CategoryChartBuilder()
                .width(width)
                .height(height)
                .theme(Styler.ChartTheme.XChart)
                .build();
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAxisTicksLineVisible(false);
        chart.getStyler().setDecimalPattern("#");
        chart.getStyler().setToolTipsEnabled(true);
        chart.addSeries("Series", xData, yData);
        return chart;
    }


}

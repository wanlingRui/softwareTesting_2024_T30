package org.ms.analyse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

/**
 * 可视化组件
 */
public class CoverageVisualizer {

    /**
     * 根据覆盖率数据生成折线图并保存到指定路径
     *
     * @param coverageData 覆盖率数据，格式为List<Entry<Date, Double>>，其中Date为执行时间，Double为覆盖率百分比
     * @param outputPath 生成的折线图的保存路径
     * @throws IOException 如果在生成或保存折线图时发生IO异常
     */
    public static void visualizeCoverage(List<Entry<Date, Double>> coverageData, String outputPath) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Entry<Date, Double> entry : coverageData) {
            dataset.addValue(entry.getValue(), "Coverage", entry.getKey().toString());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Coverage Over Time",
                "Execution Time",
                "Coverage (%)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        int width = 800;
        int height = 600;
        File lineChart = new File(outputPath);
        ChartUtils.saveChartAsPNG(lineChart, chart, width, height);
    }
}
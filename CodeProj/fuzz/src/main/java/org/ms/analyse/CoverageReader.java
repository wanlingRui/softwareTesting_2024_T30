package org.ms.analyse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 测试分析组件 使用JaCoCo来成成报告
 */
public class CoverageReader {

    /**
     * 从XML格式的覆盖率报告中读取覆盖率数据
     *
     * @param xmlReportPath XML覆盖率报告文件的路径
     * @return 包含覆盖率数据的列表，列表中的每个元素都是一个Map.Entry对象，键是日期，值是覆盖率百分比
     * @throws Exception 如果在读取XML文件或解析数据时发生异常，将抛出异常
     */
    public static List<Map.Entry<Date, Double>> readCoverageFromXML(String xmlReportPath) throws Exception {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(new File(xmlReportPath));
        Element rootNode = document.getRootElement();

        List<Map.Entry<Date, Double>> coverageEntries = new ArrayList<>();

        for (Element packageElement : rootNode.getChildren("package")) {
            for (Element clazzElement : packageElement.getChildren("class")) {
                String className = clazzElement.getAttributeValue("name");
                double classCoverage = calculateClassCoverage(clazzElement);
                Date executionTime = new Date();
                coverageEntries.add(Map.entry(executionTime, classCoverage));
            }
        }

        return coverageEntries;
    }

    /**
     * 计算类覆盖率
     *
     * @param clazzElement 类元素
     * @return 类的覆盖率百分比
     */
    private static double calculateClassCoverage(Element clazzElement) {
        int missed = Integer.parseInt(clazzElement.getChildTextTrim("counter[@type='METHOD'][@missed='true']"));
        int covered = Integer.parseInt(clazzElement.getChildTextTrim("counter[@type='METHOD'][@covered='true']"));
        int total = missed + covered;
        return total > 0 ? (double) covered / total : 0;
    }
}
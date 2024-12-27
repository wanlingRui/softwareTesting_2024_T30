package org.ms.monitor;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AFLMonitor {

    private static final String AFL_OUTPUT_DIR = "./afl_output"; // AFL输出目录
    private static final String SPECIAL_CASES_DIR = "./special_cases"; // 保存特殊测试用例的目录
    private static final Pattern COVERAGE_PATTERN = Pattern.compile("unique edges\s*:\s*(\\d+)");
    private static final Pattern EXEC_SPEED_PATTERN = Pattern.compile("exec speed\s*:\s*(\\d+)\s*execs/sec");



    public void startMonitoring() throws IOException, InterruptedException {
        System.out.println("Starting AFL monitoring...");

        Path specialCasesPath = Paths.get(SPECIAL_CASES_DIR);
        if (!Files.exists(specialCasesPath)) {
            Files.createDirectories(specialCasesPath);
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::monitorAFL, 0, 5, TimeUnit.SECONDS);
    }

    /**
     * 监控AFL的运行状态。
     *
     * 该方法会检查AFL输出目录下的fuzzer_stats文件，如果存在，则读取文件中的统计信息并解析记录。
     * 同时，该方法还会检查AFL输出目录下的queue和crashes目录，并将其中的特殊测试用例保存到指定位置。
     *
     * @throws Exception 如果在监控过程中发生异常，则抛出异常
     */
    private void monitorAFL()  {
        try {
            // 检查Fuzzer统计文件
            Path fuzzerStatsPath = Paths.get(AFL_OUTPUT_DIR, "fuzzer_stats");
            if (Files.exists(fuzzerStatsPath)) {
                List<String> stats = Files.readAllLines(fuzzerStatsPath);
                parseAndLogStats(stats);
            } else {
                System.err.println("fuzzer_stats file not found in AFL output directory.");
            }

            // 检查并保存特殊测试用例
            Path queueDir = Paths.get(AFL_OUTPUT_DIR, "queue");
            Path crashesDir = Paths.get(AFL_OUTPUT_DIR, "crashes");
            saveSpecialCases(queueDir);
            saveSpecialCases(crashesDir);
        } catch (Exception e) {
            System.err.println("Error monitoring AFL: " + e.getMessage());
        }
    }

    private void parseAndLogStats(List<String> stats) {
        for (String line : stats) {
            Matcher coverageMatcher = COVERAGE_PATTERN.matcher(line);
            Matcher execSpeedMatcher = EXEC_SPEED_PATTERN.matcher(line);

            if (coverageMatcher.find()) {
                System.out.println("Coverage: " + coverageMatcher.group(1) + " unique edges.");
            }
            if (execSpeedMatcher.find()) {
                System.out.println("Execution Speed: " + execSpeedMatcher.group(1) + " execs/sec.");
            }
        }
    }

    /**
     * 保存特殊测试用例到指定目录。
     *
     * @param dir 需要检查的目录
     * @throws Exception 如果在保存文件时发生异常，则抛出异常
     */
    private void saveSpecialCases(Path dir) throws Exception {
        if (Files.exists(dir) && Files.isDirectory(dir)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path entry : stream) {
                    if (Files.isRegularFile(entry)) {
                        Path dest = Paths.get(SPECIAL_CASES_DIR, entry.getFileName().toString());
                        if (!Files.exists(dest)) {
                            Files.copy(entry, dest);
                            System.out.println("Saved special case: " + entry.getFileName());
                        }
                    }
                }
            }
        }
    }
}
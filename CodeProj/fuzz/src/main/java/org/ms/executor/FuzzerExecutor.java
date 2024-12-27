package org.ms.executor;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FuzzerExecutor {

    private static final Logger log = Logger.getLogger(FuzzerExecutor.class);

    /**
     * 主函数，用于并发执行目标程序的测试。
     *
     * @param args 命令行参数，本例中未使用
     */
    public static void main(String[] args) {
        String targetProgramPath = "D:/nju/target.exe"; // 目标程序路径
        byte[] mutatedInput = AFLMutationOperators.flipOneByteBit("Sample input".getBytes());
        // 创建线程池，以便并发执行测试
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 提交任务并记录执行次数
        int testRunCount = 0;
        for (int i = 0; i < 100; i++) {
            testRunCount++;
            executor.submit(new TestRunner(targetProgramPath, mutatedInput));
        }

        // 关闭线程池，等待所有任务完成
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }

        System.out.printf("Total test runs: %d%n", testRunCount);
    }
}

package org.ms.executor;

import lombok.Data;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 实现的测试执行组件
 */

@Data
public class TestRunner implements Runnable{

    Logger log = Logger.getLogger(TestRunner.class);

    private final String targetProgramPath;
    private final byte[] mutatedInput;

    private long executionTime;

    public TestRunner(String targetProgramPath, byte[] mutatedInput) {
        this.targetProgramPath = targetProgramPath;
        this.mutatedInput = mutatedInput;
    }

    /**
     * 运行目标程序的方法
     *
     * @throws Exception 如果在运行过程中出现异常，将抛出异常
     */
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            // 使用ProcessBuilder创建子进程
            ProcessBuilder pb = new ProcessBuilder(targetProgramPath);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // 写入变异后的输入到子进程
            if (mutatedInput != null) {
                process.getOutputStream().write(mutatedInput);
                process.getOutputStream().flush();
                process.getOutputStream().close();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }
            reader.close();

            process.waitFor();
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            executionTime = System.currentTimeMillis() - startTime;
            System.out.printf("Test run finished. Execution time: %d ms%n", executionTime);
        }
    }


}

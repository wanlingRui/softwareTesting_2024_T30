package org.ms.scheduler;

import lombok.Data;

import java.util.BitSet;

@Data
public class TestCase {
    byte[] input;
    BitSet edgeCoverage;
    int executions;
    long lastExecutionTime;
    int score;

    public TestCase(byte[] inputData) {
        this.input = inputData;
        this.edgeCoverage = new BitSet();
        this.executions = 1;
        this.lastExecutionTime = System.currentTimeMillis();
        this.score = 0;
    }

    // 更新覆盖信息和分数
    public void updateCoverage(BitSet newCoverage, int timeNow) {
        this.edgeCoverage.or(newCoverage);
        this.executions++;
        this.lastExecutionTime = timeNow;
        this.score = edgeCoverage.cardinality() * executions; // 分数计算
    }
}

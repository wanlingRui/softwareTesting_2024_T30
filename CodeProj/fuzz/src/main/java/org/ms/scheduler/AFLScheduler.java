package org.ms.scheduler;

import java.util.*;

/**
 * AFL调度器实现
 */
public class AFLScheduler {
    private final PriorityQueue<TestCase> queue;
    private final Random rand;
    private final BitSet totalCoverage;

    public AFLScheduler() {
        this.queue = new PriorityQueue<>(Comparator.comparingInt(TestCase::getScore).reversed());
        this.rand = new Random();
        this.totalCoverage = new BitSet();
    }

    // 添加新测试用例到队列
    public void enqueueTestCase(TestCase testCase) {
        queue.offer(testCase);
        totalCoverage.or(testCase.edgeCoverage);
    }

    // 选择下一个测试用例
    public Optional<TestCase> selectNextTestCase(int currentTime) {
        if (queue.isEmpty()) return Optional.empty();

        TestCase next = queue.poll();
        next.updateCoverage(totalCoverage, currentTime); // 更新覆盖信息
        queue.offer(next); // 重新入队
        return Optional.of(next);
    }

    // 模拟AFL的一些变异操作
    public byte[] mutateInput(byte[] originalInput) {
        byte[] mutated = Arrays.copyOf(originalInput, originalInput.length);
        int choice = rand.nextInt(5);
        if (choice == 0) {
            mutated[rand.nextInt(mutated.length)] ^= 1;
        }
        return mutated;
    }

    // 使用当前选中的测试用例进行变异
    public Optional<TestCase> createMutatedTestCase(TestCase original, int currentTime) {
        byte[] mutatedData = mutateInput(original.input);
        TestCase mutatedTestCase = new TestCase(mutatedData);
        mutatedTestCase.updateCoverage(original.edgeCoverage, currentTime); // 初始覆盖继承自原用例
        enqueueTestCase(mutatedTestCase);
        return Optional.of(mutatedTestCase);
    }
}

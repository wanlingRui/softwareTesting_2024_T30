package org.ms.seedsorter;

/**
 * 被模糊测试的目标程序
 */
public interface FuzzTarget {
    Coverage run(Seed seed);
}

package org.ms.seedsorter;

import java.util.HashSet;

/**
 * 测试用的目标程序
 */
public class FuzzTargetImp implements FuzzTarget{
    @Override
    public Coverage run(Seed seed) {

        Coverage coverage = new Coverage();
        HashSet<Integer> integers = new HashSet<>();
        integers.add(1);
        coverage.setEdgeHits(integers);
        return coverage;
    }
}

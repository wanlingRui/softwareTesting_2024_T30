package org.ms.variation;

import org.ms.seedsorter.Seed;

/**
 * 定义如何对种子进行变异异算子接口 变
 */
public interface Mutator {

    Seed mutate(Seed seed);
}

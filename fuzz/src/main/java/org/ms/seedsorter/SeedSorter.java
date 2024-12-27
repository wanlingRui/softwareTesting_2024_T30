package org.ms.seedsorter;

import org.apache.log4j.Logger;
import org.ms.variation.Mutator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class SeedSorter {


    private static final Logger log = Logger.getLogger(SeedSorter.class);
    private final List<Mutator> mutators;

    public SeedSorter(List<Mutator> mutators) {
        this.mutators = mutators;
    }

    /**
     * 对种子进行排序和变异处理。
     *
     * @param seeds 初始种子列表
     * @param target 模糊测试目标
     * @return 经过排序和变异处理的种子列表
     */
    public List<Seed> sortAndMutateSeeds(List<Seed> seeds, FuzzTarget target) {
        List<Seed> mutatedSeeds = new ArrayList<>(seeds);

        for (Mutator mutator : mutators) {
            List<Seed> newMutants = mutatedSeeds.stream()
                    .map(mutator::mutate)
                    .toList();
            mutatedSeeds.addAll(newMutants);
        }

        Map<Seed, Coverage> coverageMap = mutatedSeeds.stream()
                .distinct()
                .collect(Collectors.toMap(
                        seed -> seed,
                        target::run
                ));


        return coverageMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(coverage -> {
                    Coverage cov=(Coverage) coverage;
                    return cov.getEdgeHits().size();
                }).reversed()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

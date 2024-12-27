package org.ms.seedsorter;

import org.ms.variation.ByteFlipMutator;
import org.ms.variation.InsertionDeletionMutator;
import org.ms.variation.Mutator;

import java.util.Arrays;
import java.util.List;

/**
 * 测试种子排序器
 */
public class TestSeedSorter {

    public static void main(String[] args) {
        FuzzTargetImp targetImp = new FuzzTargetImp();
        List<Seed> initialSeeds = List.of(new Seed("initial seed content".getBytes()));

        List<Mutator> mutators = Arrays.asList(new ByteFlipMutator(), new InsertionDeletionMutator());

        SeedSorter sorter = new SeedSorter(mutators);

        List<Seed> sortedSeeded = sorter.sortAndMutateSeeds(initialSeeds, targetImp);
    }
}

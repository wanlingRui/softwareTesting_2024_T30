package org.ms.variation;

import org.ms.seedsorter.Seed;

import java.util.Random;

/**
 * 变异算子实现
 */
public class ByteFlipMutator implements Mutator{

    final Random random=new Random();

    /**
     * 重写mutate方法，用于对种子进行变异操作。
     *
     * @param seed 待变异的种子
     * @return 变异后的新种子
     */
    @Override
    public Seed mutate(Seed seed) {
        byte[] content = seed.getContent();
        if (content.length>0){
            int byteFlipIndex = random.nextInt(content.length);
            content[byteFlipIndex] ^=1;
        }
        return new Seed(content);
    }
}

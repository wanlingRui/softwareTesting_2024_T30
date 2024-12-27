package org.ms.variation;

import org.ms.seedsorter.Seed;

import java.util.Random;

/**
 * 变异算子实现
 */
public class InsertionDeletionMutator implements Mutator {
    private final Random random = new Random();

    /**
     * 重写mutate方法，用于对种子进行变异操作。
     *
     * @param seed 待变异的种子
     * @return 变异后的新种子
     */
    @Override
    public Seed mutate(Seed seed) {
        byte[] content = seed.getContent();
        if (content.length > 0) {
            boolean insert = random.nextBoolean();
            int position = random.nextInt(content.length + 1);

            if (insert) {
                // 变异程序随机插入字节
                byte[] newContent = new byte[content.length + 1];
                System.arraycopy(content, 0, newContent, 0, position);
                newContent[position] = (byte)random.nextInt(256);
                System.arraycopy(content, position, newContent, position + 1, content.length - position);
                return new Seed(newContent);
            } else {
                // 删除字节
                if (position < content.length) {
                    byte[] newContent = new byte[content.length - 1];
                    System.arraycopy(content, 0, newContent, 0, position);
                    System.arraycopy(content, position + 1, newContent, position, content.length - position - 1);
                    return new Seed(newContent);
                }
            }
        }
        return seed;
    }
}
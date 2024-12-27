package org.ms.executor;

import java.util.Arrays;
import java.util.Random;

public class AFLMutationOperators {

    private static final Random RAND = new Random();

    /**
     * 简单位翻转 - 随机选择一个字节，并随机翻转其中一位。
     * @param input 原始输入数据
     * @return 变异后的数据
     */
    public static byte[] flipOneByteBit(byte[] input) {
        if (input == null || input.length == 0) return input;
        byte[] mutated = input.clone();
        int index = RAND.nextInt(input.length);
        int bit = RAND.nextInt(8);
        mutated[index] ^= (byte) (1 << bit);
        return mutated;
    }

    /**
     * 块交换 - 随机选择两个不重叠的块并交换它们。
     * @param input 原始输入数据
     * @return 变异后的数据
     */
    public static byte[] swapBlocks(byte[] input) {
        if (input.length < 2) return input;
        byte[] mutated = input.clone();
        int blockSize = RAND.nextInt(input.length / 2) + 1;
        int pos1 = RAND.nextInt(input.length - blockSize);
        int pos2 = RAND.nextInt(input.length - blockSize);
        while (pos2 <= pos1) pos2 = RAND.nextInt(input.length - blockSize); // 确保两块不重叠
        byte[] temp = Arrays.copyOfRange(mutated, pos1, pos1 + blockSize);
        System.arraycopy(mutated, pos2, mutated, pos1, blockSize);
        System.arraycopy(temp, 0, mutated, pos2, blockSize);
        return mutated;
    }

    /**
     * 插入删除 - 随机删除一个字节或在随机位置插入一个新的字节。
     * @param input 原始输入数据
     * @return 变异后的数据
     */
    public static byte[] insertOrDeleteByte(byte[] input) {
        byte[] mutated;
        if (RAND.nextBoolean() && input.length > 1) { // 删除操作
            int index = RAND.nextInt(input.length);
            mutated = new byte[input.length - 1];
            System.arraycopy(input, 0, mutated, 0, index);
            System.arraycopy(input, index + 1, mutated, index, input.length - index - 1);
        } else { // 插入操作
            mutated = new byte[input.length + 1];
            int index = RAND.nextInt(input.length + 1);
            System.arraycopy(input, 0, mutated, 0, index);
            mutated[index] = (byte) RAND.nextInt(256);
            System.arraycopy(input, index, mutated, index + 1, input.length - index);
        }
        return mutated;
    }


}
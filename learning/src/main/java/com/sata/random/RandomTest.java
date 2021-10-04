package com.sata.random;

import java.util.Arrays;
import java.util.Random;

/**
 * LC 348
 */
public class RandomTest {
    private int[] nums;
    private Random random;

    public RandomTest(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int[] reset() {
        return this.nums;
    }

    public int[] shuffle() {
        int[] copy = Arrays.copyOfRange(nums, 0, nums.length);
        for(int i = 0; i < copy.length; i++) {
            swapAt(random.nextInt(copy.length - i) + i, i, copy);
        }
        return copy;
    }

    private void swapAt(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

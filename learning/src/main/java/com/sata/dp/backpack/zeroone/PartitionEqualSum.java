package com.sata.dp.backpack.zeroone;

/**
 * LC 416, 还有LC 1049
 */
public class PartitionEqualSum {
    public boolean canPartition(int[] nums) {
        //化解为0-1背包问题了
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if(sum % 2 == 1) return false;
        int target = sum / 2;
        int[] dp = new int[target + 1]; //dp[i]表示容量为i的背包能放进去的最大重量的物品的重量是多少。
        for(int i = 0; i < nums.length; i++) {
            for(int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]); //0-1背包问题的递推公式，一维
            }
        }
        return dp[target] == target; //当dp[i]恰好等于i的时候，说明找到了一个答案使得数组被分成两部分，而且两部分的和相等。
    }
}

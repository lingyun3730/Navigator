package com.sata.dp.subarray;

/**
 * LC 718， 子数组，子串，需要定义以i-1结尾
 */
public class MaxRepeatedSubArray {

    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        //dp[i][j]表示包含nums1以i结尾，nums2以j结尾的子串匹配的最大长度。
        int[][] dp = new int[n + 1][m + 1];
        int max = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(max < dp[i][j]) max = dp[i][j];
                }
            }
        }
        return max;
    }
}

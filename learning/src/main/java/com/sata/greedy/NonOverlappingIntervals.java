package com.sata.greedy;

import java.util.Arrays;

/**
 * LC 435 这个题用dp会超时，贪心的话移除覆盖范围广的区间，
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        //还是用动态规划吧。
        //dp[i] 表示以第i个区间为最后一个区间的最多的不重叠区间个数
        //dp[i] = max(dp[j]) + 1 (j < i)
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) ->{
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(intervals[i][0] >= intervals[j][1]) { //i区间和j区间不重叠，可以作为i区间的上一个区间
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = dp[i];
        }
        return n - max;
    }

    public int eraseOverlapIntervalsII(int[][] intervals) {
        //贪心： 尽量移除那些覆盖范围广的区间，如果发生了重叠，首先应该移除那些区间终点靠后的区间
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) ->{
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int last = 0;
        int res = 0;
        for(int i = 1; i < n; i++) {
            if(intervals[last][1] > intervals[i][0]) { //说明有重叠，需要移除一个区间
                if(intervals[last][1] > intervals[i][1]) {
                    //i区间更靠前，因此移除 last的那个区间，i成为新的last，否则移除i，i的终点更加靠后，更有可能和后面的区间重叠
                    last = i;
                }
                res += 1; //移除了区间，因此结果 + 1
            }else{
                last = i; //没有重叠，不需要移除，i成为新的last
            }
        }
        return res;
    }

}

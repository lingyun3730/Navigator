package com.sata.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LC 452
 */
public class MinBurstBalloons {

    public static int findMinArrowShots(int[][] points) {
        //先按照气球的左边界对气球进行排序，然后遍历气球，更新可以用一支箭射中的一组气球的最小右边界
        //如果气球和前面的气球并不重叠，那么无法被安排到前一组中被一只箭射中，只能增加一只新箭
        //不过这个题的一个tricky的地方在于，最小右边界是更新气球的右边界，等于修改了原来的数组
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int n = points.length;
        int res = 1;//数组不为空，总是需要一支箭的
        for(int i = 1; i < n; i++) {
            if(points[i][0] > points[i-1][1]) { //前一个气球的右边界没有和后一个气球的左边界发生重叠
                res += 1;
            }else {
                points[i][1] = Math.min(points[i-1][1], points[i][1]); //更新当前组的最小右边界
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] points = {{-2147483646,-2147483645},{2147483646,2147483647}};
        int res = findMinArrowShots(points);
        System.out.println(res);
    }
}

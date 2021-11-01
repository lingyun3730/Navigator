package com.sata.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 拿红包
 * Description
 *
 * 临近年末了，因为Payments & Risk在过去的一年表现非常优异，所以Johnson决定给大家派发红包来奖励大家过去一年的努力。为了展示对公司未来发展的信心，红包里面不是软妹币现金，而是公司的股票代券。因为微信红包不支持这样的交易，所以我们就只能用实体红包。为了让红包的派发更好玩一些，Johnson决定按如下方案派发。
 *
 * Johnson准备了大量的红包，每个红包里装有一定数量的代券，其数值代表某个最小单位（比如0.001股。具体数值不重要，但这个值是固定相同的）的倍数。红包的封面写明了具体数值，大家都能看到，假设是一个1~100的整数。
 *
 * 派发开始了，以两人为一组进行，Johnson将n个（n是偶数）红包一字摊开放在桌上。你和随机分配的搭(dui)档(shou)上场轮流拿红包，每人每次只能从最左边或者最右边拿一个红包，经过n/2轮后，正好拿完。
 *
 * 作为对公司前景充满信心的你，理所当然要尽一切努力拿到更多的股票代券。你搭档也一样，他/她也会尽一切努力去拿尽可能多的股票代券。
 *
 * 假设你先拿，比如桌上的红包是这样的情况（从左到右）：100，42，3， 94， 3，71
 *
 * 那么你的最佳策略是：先拿100，你的搭档会拿71，你再拿42，你的搭档会再拿3，你再拿94，然后你的搭档拿最后剩下的3。这种策略下，你总共拿到236，搭档总共拿到77。搭档在你的阻击下，无法做得比这更好。你在搭档的阻击下，也无法做得更好了。
 *
 * 当桌上的红包数量一多，问题就变得复杂一些了，你们俩需要耗费更多的脑力进行博弈对抗。比如桌面上有：
 *
 * 94, 61, 29, 76, 23, 25, 37, 24, 1, 85, 98, 27
 *
 * 此时，你俩的最佳策略是：
 *
 * 左、L、右、R、右、L、左、L、左、L、左、last
 *
 * 其中中文代表你的选择，英文则代表搭档的选择。该策略下，你总共得到331，搭档则得到249。同样地，这是你俩激烈对抗下的最优解，你没法得到更多，你搭档也无法得到更多了。
 *
 * 你逐渐意识到，其实这些红包一摆到桌面上，你和搭档分别最多能拿多少就已经确定了（Johnson在后面偷笑，其实这就是他想给你们俩的股票数量），但需要你们俩都非常小心，每一步都不能走错，才能各自拿到最大值，否则就会让自己的一部分股票代券送给对方。
 *
 * 于是你拿出电脑，打开IDE，先算一下自己到底最多能拿到多少。
 *
 *
 * Input
 * 仅一行，代表桌上的红包情况，从左到右。
 *
 *
 * Output
 * 仅一行，代表你能拿到的最大数量。
 *
 *
 * Sample Input 1
 *
 * 50 32 66 90
 * Sample Output 1
 *
 * 140
 *
 * Sample Input 2
 *
 * 94 61 29 76 23 25 37 24 1 85 98 27
 * Sample Output 2
 *
 * 331
 *
 * Sample Input 3
 *
 * 71 49 28 63 52 100 60 68 61 78 70 13 14 92 43 70 48 42 71 8
 * Sample Output 3
 *
 * 605
 *
 * Sample Input 4
 *
 * 99 32 92 77 48 70 70 84 32 65 18 12 89 2 26 91 27 60 64 55 5 59 95 45 48 30 28 89 54 84
 * Sample Output 4
 *
 * 870
 */

/**
 * 博弈型dp, 对角线填充, totally bottom -> up, 二维dp
 *
 * dp[i][j][0] 表示面对i-j的红包自己作为先手的最大收益
 * dp[i][j][1] 表示面对i-j的红包自己作为后手的最大收益
 * dp[i][j][0] = max(dp[i+1][j][1] + data[i], dp[i][j-1][1] + data[j])
 * dp[i][j][1] = dp[i+1][j][1] + data[i] > dp[i][j-1][1] + data[j]? dp[i+1][j][0] : dp[i][j-1][0]
 */
public class GameDp {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        String s = null;
        s = in.nextLine();
        String[] s2 = s.split(" ");
        List<Integer> data = new ArrayList<>();
        for(int i = 0 ; i < s2.length ; i ++){
            data.add(Integer.valueOf(s2[i]));
        }
        System.out.println(helper2(data));
        in.close();
    }
    private static int helper(List<Integer> data) {
        int n = data.size();
        int[][][] dp = new int[n][n][2];
        //对角线初始化
        for(int i = 0; i < n; i++) { //数组长度为1，
            dp[i][i][0] = data.get(i); //先手拿掉，
            dp[i][i][1] = 0; //后手没有拿到
        }
        //沿对角线的enrich方式
        for(int l = 2; l <= n; l++) { //对角线填充完了，遍历数组长度，从对角线到上填充，因为必须保证i <= j
            for(int i = 0; i <= n-l; i++) {
                int j = i+l-1;
                // dp[i][j][0] 表示面对i->j位置的红包，先手能拿到的最大值
                int left = dp[i+1][j][1] + data.get(i); //拿了左边的红包，则面对（i+1 -> j）位置的红包，自己变成了后手
                int right = dp[i][j-1][1] + data.get(j); //拿了左边的红包，则面对（i -> j-1）位置的红包，自己变成了后手
                dp[i][j][0] = Math.max(left, right); //面对i -> j自己是先手的情况
                //dp[i][j][1] 表示面对i->j位置的红包，后手能拿到的最大值
                if(left > right) {
                    dp[i][j][1] = dp[i+1][j][0]; //先手选择了左边，后手面对（i+1 -> j)位置的红包，变成了先手
                }else{
                    dp[i][j][1] = dp[i][j-1][0]; //先手选择了右边，后手面对（i -> j-1)位置的红包，变成了先手
                }
            }
        }
        return Math.max(dp[0][n-1][0], dp[0][n-1][1]);
    }

    //数组的填充方式是从下向上从左往右。
    private static int helper2(List<Integer> data) {
        int n = data.size();
        int[][][] dp = new int[n][n][2];
        //对角线初始化
        for(int i = 0; i < n; i++) { //数组长度为1，
            dp[i][i][0] = data.get(i); //先手拿掉，
            dp[i][i][1] = 0; //后手没有拿到
        }
        //从下向上，从左往右的遍历方式
        for(int i = n-1; i >=0; i--) {
            for(int j = i + 1; j < n; j++) {
                int left = dp[i+1][j][1] + data.get(i);
                int right = dp[i][j-1][1] + data.get(j);
                dp[i][j][0] = Math.max(left, right); //作为先手
                if(left > right) {
                    dp[i][j][1] = dp[i + 1][j][0];
                }else{
                    dp[i][j][1] = dp[i][j-1][0];
                }
            }
        }
        return Math.max(dp[0][n-1][0], dp[0][n-1][1]);
    }
}

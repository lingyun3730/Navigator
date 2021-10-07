package com.sata.dp.treeDp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeDp {
    /**
     * 圣诞party
     * Description
     *
     * 圣诞节马上就要来了，为了带动节日氛围并营造一个难得的交(tu)流(cao)机会，Payments/Risk 3位很会浪的同事决定在外面包一个豪华别墅，并组织邀请部门同事一起轰趴欢度。
     *
     * 为了避免场面过于火爆，交(tu)流(cao)过于激烈，出现尴尬的局面，组织者决定，不能同时邀请某位同事和他的直属老板同时参加该盛宴。如果把整个Payments/Risk的组织架构画出来，可以形成一颗树状结构，也就是说，只能挑这颗“树”上的部分节点参加。
     *
     * 为了进一步提升活动的效果，增强友谊，防止出现意外的言论，组织者决定尽可能邀请友好度较高的同事参加。于是他们开了一个周末的会，把Payments/Risk所有同事都讨论了一遍，并且根据他们的观察，给每位同事打了一个友好分值。
     *
     * 那么组织者剩下的问题就是想在这个大树上，在上述限制条件下，邀请尽可能多的同事参加，并使得友好分值总和尽可能大。于是他们找到了你，希望你能帮他们解决这个问题并告诉他们最高可以能得多少分。
     *
     * 我们已经对数据进行了处理，从0开始顺序编码，每个同事都有一个ID。
     *
     *
     * Input
     * 输入包含三行：
     *
     * 第一行，N，表示同事总数，不超过1000。
     *
     * 第二行，N个正整数（0～100）之间，按ID顺序表示这n个同事的友好度分数，
     *
     * 第三行，N个整数（-1～N-1），按ID顺序表示此同事的上级ID，最大老板的上司ID为-1。
     *
     *
     * Output
     * 输出一个数字，最大值
     *
     *
     * Sample Input 1
     *
     * 7
     * 1 1 1 1 1 1 1
     * 2 2 4 4 -1 3 3
     * Sample Output 1
     *
     * 5
     * @param args
     */
    /**
     * 树形dp，员工之间的上下级关系hierarchy天然形成了一个tree, 相邻层级之间的节点存在互斥关系，标记每个node的两个状态：select/not select.
     * @param args
     */
    public static void main(String[] args) {
        int total = 0;
        try {
            total = Reader.nextInt();

            List<Integer> scores = new ArrayList<>();
            List<Integer> bosses = new ArrayList<>();
            for (int i = 0; i < total; i++) {
                scores.add(Reader.nextInt());
            }
            for (int j = 0; j < total; j++) {
                bosses.add(Reader.nextInt());
            }
            //relation map, tree.
            Map<Integer, List<Integer>> relations = getRelations(bosses);
            int[] res = dfs(-1, relations.get(-1), relations, scores);
            System.out.println(Math.max(res[0], res[1]));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param node : current node
     * @param children : children nodes of current node
     * @param relations : overall relations.
     * @param scores : friendship score.
     * @return
     */
    private static int[] dfs(int node, List<Integer> children, Map<Integer, List<Integer>> relations, List<Integer> scores) {
        int[] result = new int[2];
        if(children == null || children.isEmpty()) {
            result[1] += scores.get(node);
            return result;
        }
        List<int[]> midVal = new ArrayList<>();
        for(int i : children) {
            int [] tmp = dfs(i, relations.get(i), relations, scores); //子节点继续dfs。
            midVal.add(tmp);
        }
        for(int[] tmp : midVal) {
            result[0] += Math.max(tmp[0], tmp[1]); //不选择该node， child nodes可选可不选
            result[1] += tmp[0]; //选择该node, 则child node不能被选
        }
        int score = node == -1? 0 : scores.get(node);
        result[1] += score; //选择该node，所以要加上它的score
        return result;
    }

    private static Map<Integer, List<Integer>> getRelations(List<Integer> bosses) {
        Map<Integer, List<Integer>> mp = new HashMap<>(); //boss -> staffs
        for (int i = 0; i < bosses.size(); i++) {
            if (mp.get(bosses.get(i)) == null) {
                List<Integer> l = new ArrayList<>();
                l.add(i);
                mp.put(bosses.get(i), l);
            } else {
                mp.get(bosses.get(i)).add(i);
            }
        }
        return mp;
    }
}

class Reader {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }
}


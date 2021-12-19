package com.sata.OJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// dfs 暴力搜索
public class CakeCut {

    private static int[][] mode0 = {
            {0, 0, 1, 0, 0, 1, 0, 1, 1},
            {1, 0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 1, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 1, 0, 0}
    };

    private static int[][] mode1= {
            {1, 0, 0, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1, 1, 0, 0, 1},
            {0, 1, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 1, 0, 1, 0, 0}
    };

    private static int[][] mode2 = {
            {0, 1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1, 0},
            {1, 0, 1, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 1, 0, 1},
    };

    private static int[][] mode3 = {
            {1, 0, 1, 0, 0, 1, 1, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 1, 1},
            {0, 0, 1, 1, 0, 0, 1, 0, 1},
            {1, 1, 0, 0, 0, 0, 1, 0, 1}
    };

    static List<int[][]> modes = Arrays.asList(mode0, mode1, mode2, mode3);

    private static boolean helper(List<int[]> cakes, int[] cnt, List<Integer> res, int index, boolean[] visited) {
        if(index >= 4 && allCakesMeet(cakes, cnt, visited)) {
            return true;
        }
        for(int i = index; i < 4; i++) { //cakes
            for(int j = 0; j < 4; j++) { //modes
                if(visited[j]) continue;
                for(int k = 0; k < modes.get(j).length; k ++) { //specific patterns in each modes
                    List<int[]> cpcakes = new ArrayList<>();
                    for(int[] c : cakes) {
                        int[] cp = Arrays.copyOf(c,  9);
                        cpcakes.add(cp);
                    }
                    doMatch(cpcakes, i, res, modes.get(j)[k], j);
                    visited[j] = true;
                    if(helper(cpcakes, cnt, res, i + 1, visited)) return true;
                    res.remove(res.size() - 1);
                    visited[j] = false;
                }
            }
        }
        return false;
    }

    private static void doMatch(List<int[]> cakes, int i, List<Integer> res,  int[] mode, int j) {
        int[] cake = cakes.get(i);
        for(int v = 0; v < 9; v++) {
            if(mode[v] == 1) {
                cake[v] = cake[v] & 0xf;
            }else{
                cake[v] = 0;
            }
        }
        res.add(j);
    }

    private static boolean allCakesMeet(List<int[]> cakes, int[] cnt, boolean[] visited) {
        int[] cur = new int[5];
        for(int[] cake : cakes) {
            for(int i = 0; i < 9; i++) {
                if(cake[i] > 0) cur[cake[i]]++;
            }
        }
        for(int i = 0; i < 5; i++) {
            if(cnt[i] != cur[i]) return false;
        }
        for(boolean visit : visited) {
            if(! visit) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //输入目标
        try {
            String obj = Reader.nextLine();
            String[] objs = obj.split(" ");
            int[] cnt = new int[5];
            for(String o : objs) {
                switch (o){
                    case "S":
                        cnt[1]++;
                        break;
                    case "K":
                        cnt[2]++;
                        break;
                    case "C":
                        cnt[3]++;
                        break;
                    case "N":
                        cnt[4]++;
                        break;
                }
            }
            Reader.nextLine(); //空行

            //输入四个蛋糕
            List<int[]> cakes = new ArrayList<>();
            for(int i = 0; i < 4; i++) {
                int[] cake = new int[9];
                int index = 0;
                for(int j = 0; j < 3; j++) {
                    String line = Reader.nextLine();
                    for(char c : line.toCharArray()) {
                        switch (c) {
                            case '-':
                                cake[index++] = 0;
                                break;
                            case 'S':
                                cake[index++] = 1;
                                break;
                            case 'K':
                                cake[index++] = 2;
                                break;
                            case 'C':
                                cake[index++] = 3;
                                break;
                            case 'N':
                                cake[index++] = 4;
                                break;
                        }
                    }
                }
                cakes.add(cake);
                if(i < 3) Reader.nextLine(); //空行
            }
            // dfs
            List<Integer> res = new ArrayList<>();
            boolean[] visited = new boolean[4];
            boolean result = helper(cakes, cnt, res, 0, visited);

            //output the result
            if(result) {
                for (int re : res) {
                    System.out.println(re);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

package com.sata.bfs;

import java.util.*;

/**
 * LC 301
 */
public class ValidParentheses {
    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(s);
        visited.add(s);
        boolean found = false;
        while(! q.isEmpty()) {
            for(int i = q.size(); i > 0; i--) {
                String tmp = q.poll();
                if(isValid(tmp)) {
                    found = true;
                    res.add(tmp);
                }
                // no found this layer, explore the next layer
                if(! found) {
                    for(int j = 0; j < tmp.length(); j++) {
                        if(tmp.charAt(j) == '(' || tmp.charAt(j) == ')') {
                            String sStr = tmp.substring(0, j) + tmp.substring(j + 1);
                            if(! visited.contains(sStr)) {
                                visited.add(sStr);
                                q.add(sStr);
                            }
                        }
                    }
                }
            }
            if(found) break; // found minimized results in this layer, won't go through the next layer
        }
        return res;
    }

    private static boolean isValid(String str) {
        int cnt = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') cnt ++;
            else if(str.charAt(i) == ')') cnt --;
            if(cnt < 0) return false;
        }
        return cnt == 0;
    }

    public static void main(String[] args) {
        List<String> res = removeInvalidParentheses(")(");
        System.out.println(res);
    }
}

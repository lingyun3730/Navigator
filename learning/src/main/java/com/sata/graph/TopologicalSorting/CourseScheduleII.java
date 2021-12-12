package com.sata.graph.TopologicalSorting;

import java.util.*;

/**
 * LC 210
 */
public class CourseScheduleII {
    public int[] findOrder(int num, int[][] p) {
        Map<Integer, List<Integer>> mp = new HashMap<>();
        int[] indegree = new int[num];
        for(int[] den : p) {
            List<Integer> l = mp.getOrDefault(den[1], new ArrayList<>());
            l.add(den[0]);
            mp.put(den[1], l);
            indegree[den[0]] ++; //den[0]的依赖+1
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < num; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        int[] res = new int[num];
        int index = 0;
        int count = 0;
        while(! q.isEmpty()) {
            int course = q.poll();
            count ++;
            res[index++] = course;
            if(! mp.containsKey(course)) continue;
            for(int next : mp.get(course)) {
                indegree[next] --;
                if(indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        return count == num ? res : new int[] {};
    }
}

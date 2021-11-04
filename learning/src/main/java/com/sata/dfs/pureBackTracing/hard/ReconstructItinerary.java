package com.sata.dfs.pureBackTracing.hard;

import java.util.*;

/**
 * LC 332, 和37题一样，都是找到一个答案就返回。
 */
public class ReconstructItinerary {
    public static List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        res.add("JFK");
        Map<String, Map<String, Integer>> mp = new HashMap<>(); //<start, <end, number>> 为了避免成环
        for(List<String> ticket : tickets) {
            String start = ticket.get(0);
            String end = ticket.get(1);
            if(! mp.containsKey(start)) {
                Map<String, Integer> tmp = new TreeMap<>(); //目的地按照字母顺序从小到大排序
                tmp.put(end, 1);
                mp.put(start, tmp);
            }else{
                Map<String, Integer> tmp = mp.get(start);
                tmp.put(end, tmp.getOrDefault(end, 0) + 1);
                mp.put(start, tmp);
            }
        }
        helper(res, mp, tickets.size());
        return res;
    }

    //这是求一条通路的问题，因此当找到这个通路就结束，有返回值，返回值就是true/false。同样的题目还有解数独问题。
    private static boolean helper(List<String> res, Map<String, Map<String, Integer>> mp, int ticketNumber) {
        if(res.size() == ticketNumber + 1) { //路线长度等于ticket number + 1, 只寻找一条路径即可
            return true;
        }
        String lastStation = res.get(res.size() - 1);
        if(mp.containsKey(lastStation)) {
            for(String targetStation : mp.get(lastStation).keySet()) { //target station
                int remain = mp.get(lastStation).get(targetStation);
                if(remain <= 0) {
                    continue;
                }
                mp.get(lastStation).put(targetStation, remain - 1);
                res.add(targetStation);
                if(helper(res, mp, ticketNumber)) return true; //找到答案要及时返回
                res.remove(res.size() - 1);
                mp.get(lastStation).put(targetStation, remain);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<String>> strs = new ArrayList<>();
        //[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        List<String> str = new ArrayList<>();
        str.add("JFK");
        str.add("SFO");
        strs.add(str);
        List<String> str1 = new ArrayList<>();
        str1.add("JFK");
        str1.add("ATL");
        strs.add(str1);
        List<String> str2 = new ArrayList<>();
        str2.add("SFO");
        str2.add("ATL");
        strs.add(str2);
        List<String> str3 = new ArrayList<>();
        str3.add("ATL");
        str3.add("JFK");
        strs.add(str3);
        List<String> str4 = new ArrayList<>();
        str4.add("ATL");
        str4.add("SFO");
        strs.add(str4);
        List<String> res = findItinerary(strs);
        res.forEach(System.out::println);
    }
}

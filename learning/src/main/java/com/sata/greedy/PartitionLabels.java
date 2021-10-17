package com.sata.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 763 https://leetcode.com/problems/partition-labels/
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        //第一次遍历找到每个字符的最远出现的位置下标
        //第二次遍历找分割点，分割点在于找到遍历过程中之前出现的所有字符的最远下标，当当前下标等于这个最远下标
        //的时候，就是找到了一组，当前下标就是分割点
        int[] hash = new int[26];
        char[] chs = s.toCharArray();
        for(int i = 0;  i < chs.length; i++) {
            hash[chs[i] - 'a'] = i;
        }
        int idx = 0; //之前出现过的所有字符的最远下标
        int last = -1; //上一组字母的最后位置下标
        for(int i = 0; i < chs.length; i++) {
            idx = Math.max(idx, hash[chs[i] - 'a']);
            if(idx == i) {
                res.add(i - last); //结果要求返回的是长度。
                last = idx; //更新上一组字母的最后位置下标
            }
        }
        return res;
    }
}

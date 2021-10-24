package com.sata.string;

/**
 * KMP algorithm
 * find the first match point of t in s
 *t = "abababca"
 *s = "ababababca"
 *return 2;
 */
public class StrStr {
    //求next数组，表示子串的最长前缀后缀匹配长度。
    private void getNext(int[] next, String p) {
        next[0] = 0;
        int j = 0; //needle串作为前缀串进行匹配
        for(int i = 1; i < p.length(); i++) { //主串循环，错开一位作为后缀串进行匹配，所以后缀串从1开始
            while(j > 0 && p.charAt(i) != p.charAt(j)) {
                j = next[j-1]; //回退到匹配上的部分
            }
            if(p.charAt(i) == p.charAt(j)) {
                j ++;
            }
            next[i] = j;
        }
    }
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if(n == 0) return 0;
        int[] next = new int[n]; // next数组
        getNext(next, needle); //生成next数组
        int j = 0;
        for(int i = 0; i < haystack.length(); i++) {
            while(j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j-1]; //后退到最近能匹配到的位置, 注意是next[j-1]。
            }
            if(haystack.charAt(i) == needle.charAt(j)) { //对位字符能匹配， j+1
                j ++;
            }
            if(j == needle.length()) { //j已经走完了，匹配上了
                return (i - j + 1);
            }
        }
        return -1;
    }
}

package com.sata.string;

/**
 * KMP algorithm
 * find the first match point of t in s
 *t = "abababca"
 *s = "ababababca"
 *return 2;
 */
public class StrStr {
    /**
     * KMP算法是用来解决字符串匹配问题的， 一边是主串，一边是模式串，当模式串在某一位不能匹配上主串时，模式串不需要重新从0开始匹配，而是可以回退到上一个
     * 可以完全匹配上的地方，想一下 模式串aabaaf和主串aabaabaaf, f和b不匹配，模式串回退到b的位置继续和主串进行匹配，因此我们需要记录下模式串的next数组，
     * 这个数组的作用是，记录模式串的子串的最长前缀后缀匹配的长度，作为匹配时回退的依据，求next数组的过程是模式串自身和自身进行匹配的过程，后缀串和前缀串
     * 错开一位，后缀串从下标1开始，前缀串从下标0开始，分别考虑当匹配和不匹配时的操作，不匹配时，前缀串需要回退到最长前缀后缀长度的下标位置，匹配时，
     * 前缀串向前走一位，此时可以求出后缀串next[i] = j，表示，模式串[0,i]的最长前缀后缀的长度就是j。
     *
     * 然后模式串和主串进行匹配，当模式串和主串不匹配时，模式串根据next数组回退到最长前缀后缀相等的长度的位置，匹配时模式串向前走一位，等模式串走到最后一位，
     * 就能求出主串中匹配模式串的第一个位置下标。
     */


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

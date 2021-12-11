package com.sata.bitsolution;

/**
 * LC 421: 字典树 + 比特位
 */
public class XorMaximum {

    class Node {
        Node[] children = new Node[2];//0,1
        int num;
    }
    Node root = new Node();
    public int findMaximumXOR(int[] nums) {
        //可以用字典树来做，先把数组中的数字填进字典树里。
        Node node = root;
        for(int num : nums) {
            node = root;
            for(int i = 31; i >=0; i--) {
                int c = (num >> i) & 1;
                if(node.children[c] == null) {
                    node.children[c] = new Node();
                }
                node = node.children[c];
            }
            node.num = num;
        }
        int maxValue = Integer.MIN_VALUE;
        for(int num : nums) {
            int curValue = 0;
            node = root;
            for(int i = 31; i >= 0; i--) {
                int c = (num >> i) & 1; //取对应比特位
                //异或1取反
                if(node.children[c ^ 1] != null) { //异或1有值，说明和当前数字的比特位相反
                    curValue += 1 << i;
                    node = node.children[c ^ 1];
                }else{
                    //&1取改位
                    node = node.children[c & 1];
                }
            }
            maxValue = Math.max(maxValue, curValue); //不断更新maxValue.
        }
        return maxValue;
    }
}

package com.sata.others.learning;

class BFS {
    public static boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() == 0) return s2.equals(s3);
        if(s2.length() == 0) return s1.equals(s3);
        if(s1.length() + s2.length() != s3.length()) return false;
        int i1 = 0, i2 = 0;
        for(int i = 0; i < s3.length(); i++) {
            if(i1<s1.length() && s3.charAt(i) == s1.charAt(i1)) {
                i1++;
            }else if(i2<s2.length() && s3.charAt(i) == s2.charAt(i2)){
                i2++;
            } else{
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        boolean res = isInterleave(s1, s2, s3);
        System.out.println(res);
    }

}

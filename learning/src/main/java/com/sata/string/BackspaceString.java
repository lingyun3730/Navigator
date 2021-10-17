package com.sata.string;

/**
 * LC 844 : 快慢指针
 */
public class BackspaceString {

    public boolean backspaceCompare(String s, String t) {
        //快慢指针
        String sr = deleteCompute(s);
        String tr = deleteCompute(t);
        return sr.equals(tr);
    }

    private String deleteCompute(String s) {
        StringBuilder sb = new StringBuilder();
        int fast = 0;
        while(fast < s.length()) {
            if(s.charAt(fast) == '#') {
                if(sb.length() != 0)
                    sb.deleteCharAt(sb.length() - 1);
            }else{
                sb.append(s.charAt(fast));
            }
            fast ++;
        }
        return sb.toString();
    }
}

package com.sata.others.learning;

import java.util.HashMap;
import java.util.Map;

class Test1 {
    public boolean isAnagram(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        if(sLength != tLength) return false;
        Map<Character, Integer> mp = new HashMap<>();
        for(Character  c : s.toCharArray()) {
            if(! mp.containsKey(c)){
                mp.put(c, 1);
            }else{
                int val = mp.get(c);
                mp.put(c, val+1);
            }
        }
        for(Character c : t.toCharArray()){
            if(mp.containsKey(c) && mp.get(c) > 0) {
                int val = mp.get(c);
                mp.put(c, val-1);
            }else{
                return false;
            }
        }

        return true;
    }
}

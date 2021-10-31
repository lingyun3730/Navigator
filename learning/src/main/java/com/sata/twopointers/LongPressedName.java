package com.sata.twopointers;

/**
 * LC 925, 双指针的题目，比较经典
 */
public class LongPressedName {
    public boolean isLongPressedName(String name, String typed) {
        //双指针
        int nameIndex = 0;
        int typedIndex = 0;
        while(typedIndex < typed.length() && nameIndex < name.length()) {
            if(name.charAt(nameIndex) == typed.charAt(typedIndex)) { //相等的情况
                nameIndex++;
                typedIndex++;
            }else{
                if(typedIndex == 0) return false; //第一位就不匹配，则直接返回false
                while(typedIndex < typed.length() && typed.charAt(typedIndex) == typed.charAt(typedIndex - 1))
                    typedIndex ++; //让typed 跨越相同的字符
                if(typedIndex < typed.length() && name.charAt(nameIndex) == typed.charAt(typedIndex)) { //typed跨越了相同的字符之后相等，则算是匹配上了
                    nameIndex ++;
                    typedIndex ++;
                }else{
                    return false;
                }
            }
        }
        if(nameIndex < name.length()) return false; //name没有匹配完成，直接返回false
        while(typedIndex < typed.length()) { //typed没有匹配完成，继续向前走
            if(typed.charAt(typedIndex) == typed.charAt(typedIndex - 1)) typedIndex ++;
            else return false;
        }
        return true;
    }
}

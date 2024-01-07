package com.sata.jvm;

import java.util.ArrayList;
import java.util.List;

public class TryInitMain {
    public static void main(String[] args) {
        ChildClass cc = new ChildClass();
        cc.printX();
        List list = new ArrayList();
        list.add("123");
        List<Integer>  list2 = list;
        System.out.println(list2.get(0).intValue());
    }
}
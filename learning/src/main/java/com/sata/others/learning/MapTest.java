package com.sata.others.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {
    public static Map<String, Student> filterTest() {
        Map<String, Student> mp = new HashMap<>();
        Student stu1 = new Student(12, "lili");
        Student stu2 = new Student(14, "zhangsan");
        stu1.addChildren("lisi");
        stu1.addChildren("2");
        stu2.addChildren("3");
        stu2.addChildren("4");
        mp.put(stu1.getName(), stu1);
        mp.put(stu2.getName(), stu2);
        StringBuilder builder = new StringBuilder();
        mp.get("lili")
                .getChildren()
                .stream()
                .filter(o -> o.length() <=1)
                .forEach(builder::append);
        System.out.println(builder.toString());
        return mp;
    }

    public static void main(String[] args) {
        filterTest();
        System.out.println("finished");
    }
}

class Student{
    int age;
    String name;
    List<String> children;
    public Student(int age, String name) {
        this.name = name;
        this.age = age;
        children = new ArrayList<>();
    }
    public void increaseAge() {
        age++;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public List<String> getChildren() {
        return children;
    }

    public void addChildren(String child) {
        children.add(child);
    }
}

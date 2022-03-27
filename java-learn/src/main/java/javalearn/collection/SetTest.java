package javalearn.collection;

import java.util.*;

public class SetTest {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet();

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet();

        TreeSet<String> treeSet = new TreeSet<>();

        List<String> list = Arrays.asList("A", "T", "B", "F", "M", "Z");
        list.forEach(l -> {
            hashSet.add(l);
            linkedHashSet.add(l);
            treeSet.add(l);
        });
        System.out.println("HashSet:" + hashSet);
        System.out.println("linkedHashSet:" + linkedHashSet);
        System.out.println("treeSet:" + treeSet);
    }
}

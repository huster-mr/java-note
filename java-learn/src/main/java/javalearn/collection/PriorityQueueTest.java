package javalearn.collection;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(10, 3, 9, 6, 8, 4, 5, 1);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        System.out.println("-----开始添加元素---------");
        list.forEach(i -> {
            priorityQueue.offer(i);
            System.out.println("当前的最小元素：" + priorityQueue.peek());
        });

        System.out.println("-----开始移除元素---------");
        int count = priorityQueue.size();
        for (int i = 0; i < count; i++) {
            System.out.println("当前的最小元素：" + priorityQueue.peek());
            priorityQueue.poll();
        }
    }
}

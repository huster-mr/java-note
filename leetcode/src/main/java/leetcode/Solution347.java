package leetcode;

import java.util.*;
import java.util.stream.Collectors;

// 347.前K个高频元素
public class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        map.entrySet().stream().forEach(entry -> {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(entry);
            } else if (priorityQueue.peek().getValue() < entry.getValue()) {
                priorityQueue.poll();
                priorityQueue.offer(entry);
            }
        });
        List<Integer> list = priorityQueue.stream().map(Map.Entry::getKey).collect(Collectors.toList());
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}

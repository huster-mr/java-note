package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i=0; i < nums.length; i++) {
            Integer key = target - nums[i];
            if (numMap.containsKey(key)) {
                return new int[]{i, numMap.get(key)};
            }
            numMap.put(nums[i], i);
        }
        return null;
    }
}

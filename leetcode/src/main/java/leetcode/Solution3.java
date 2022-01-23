package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 3.无重复字符的最长子串
 * 动态规划：f(i)表示以当前字母结尾的，最长不含重复的字符串的长度
 * 两种情况
 * a: f(i) = 当前位置-上一次出现的位置；当前字母已经出现过了，同时当前字母距离上一次的距离小于等于f(i-1);
 * b: f(i) = f(i-1) + 1； 当前字母没有出现过，或者当前字符距离上一次出现的距离大于f(i-1);
 *
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int maxLen = 0;
        int curLen = 0;
        Map<Character, Integer> positionMap = new HashMap<>();
        for(int i=0; i<len; i++) {
            char ch = s.charAt(i);
            if (positionMap.containsKey(ch) && i-positionMap.get(ch) <= curLen) {
                curLen = i - positionMap.get(ch);
            } else {
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            }
            positionMap.put(ch, i);
        }
        return maxLen;
    }
}

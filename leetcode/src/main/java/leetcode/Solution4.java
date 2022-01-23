package leetcode;

public class Solution4 {
    // 自己首次的代码
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int n = nums1.length + nums2.length;
//        boolean isOdd = (n % 2 == 1 ? true : false);
//        int mid = n / 2;
//        double res = 0;
//        int n1 = 0;
//        int n2 = 0;
//        for (int i=0; i<n; i++) {
//            int temp = 0;
//            if (n1 == nums1.length) {
//                temp = nums2[n2];
//                n2++;
//            } else if (n2 == nums2.length) {
//                temp = nums1[n1];
//                n1++;
//            } else {
//                temp = Math.min(nums1[n1],nums2[n2]);
//                if (nums1[n1] > nums2[n2]) {
//                    n2++;
//                } else {
//                    n1++;
//                }
//            }
//            if (i == mid - 1) {
//                res = temp;
//            } else if (i == mid) {
//                if (isOdd) {
//                    return temp;
//                } else {
//                    return (res + temp) / 2;
//                }
//            }
//        }
//        return 0;
//    }
//

    // 发一：别人的代码
//    public double findMedianSortedArrays(int[] A, int[] B) {
//        int a = A.length;
//        int b = B.length;
//        int len = a + b;
//        int pre = 0,cur = 0;
//        int aStart = 0;
//        int bStart = 0;
//        for (int i = 0; i <= len/2; i++) {
//            pre = cur;
//            if (aStart < a && (bStart >= b || A[aStart] < B[bStart])) {
//                cur = A[aStart++];
//            } else {
//                cur = B[bStart++];
//            }
//        }
//        if ((len & 1) == 1) {
//            return cur;
//        } else {
//            return (cur + pre) / 2.0;
//        }
//    }

    // 方法二：每次排除一半不可能的值；
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int left = (n1 + n2 + 1) / 2;
        int right = (n1 + n2 + 2) / 2;
        return (getKth(nums1, 0, n1-1, nums2, 0, n2-1, left)
                + getKth(nums1, 0, n1-1, nums2, 0, n2-1, right)) / 2.0;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (len2 == 0) {
            return nums1[start1 + k -1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int i = start1 + Math.min(len1, k/2) - 1;
        int j = start2 + Math.min(len2, k/2) - 1;
        if (nums1[i] < nums2[j]) {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        } else {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
    }
}

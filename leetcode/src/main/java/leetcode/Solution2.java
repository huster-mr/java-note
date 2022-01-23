package leetcode;

// 2.两数相加
public class Solution2 {
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        return addTwoNumbersCore(l1, l2, 0);
//    }
//    // 递归写法
//    private ListNode addTwoNumbersCore(ListNode l1, ListNode l2, int carry) {
//        if (l1 == null && l2 == null) {
//            if (carry == 0) {
//                return null;
//            } else {
//                return new ListNode(carry);
//            }
//        }
//        int sum = 0;
//        ListNode listNode = new ListNode();
//        sum += (l1 == null ? 0 : l1.val);
//        sum += (l2 == null ? 0 : l2.val);
//        listNode.val = sum % 10;
//        l1 = (l1 == null ? null : l1.next);
//        l2 = (l2 == null ? null : l2.next);
//        listNode.next = addTwoNumbersCore(l1, l2, sum/10);
//        return listNode;
//    }

    // 非递归写法
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            sum += (l1 == null ? 0 : l1.val);
            sum += (l2 == null ? 0 : l2.val);
            ListNode listNode = new ListNode();
            listNode.val = sum % 10;
            carry = sum / 10;
            node.next = listNode;
            node = listNode;
            l1 = (l1 == null ? null : l1.next);
            l2 = (l2 == null ? null : l2.next);
        }
        if (carry != 0) {
            ListNode listNode = new ListNode();
            listNode.val = carry;
            node.next = listNode;
        }
        return head.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

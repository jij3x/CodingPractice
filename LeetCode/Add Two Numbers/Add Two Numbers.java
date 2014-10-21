/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode start = new ListNode(0), tail = start;
        while (l1 != null || l2 != null || carry != 0) {
            carry += (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            tail.next = new ListNode(carry % 10);
            tail = tail.next;
            carry /= 10;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return start.next;
    }
}

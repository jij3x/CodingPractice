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
    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode(0), greater = new ListNode(0), lp = less, gp = greater;
        while (head != null) {
            if (head.val < x) {
                lp.next = head;
                lp = lp.next;
            } else {
                gp.next = head;
                gp = gp.next;
            }
            head = head.next;
        }
        gp.next = null;
        lp.next = greater.next;
        return less.next;
    }
}

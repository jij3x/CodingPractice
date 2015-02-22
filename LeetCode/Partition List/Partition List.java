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
        ListNode less = new ListNode(0), more = new ListNode(0), lp = less, mp = more;
        for (; head != null; head = head.next) {
            if (head.val < x) {
                lp.next = head;
                lp = lp.next;
            } else {
                mp.next = head;
                mp = mp.next;
            }
        }
        mp.next = null;
        lp.next = more.next;
        return less.next;
    }
}

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
    public ListNode swapPairs(ListNode head) {
        ListNode safeGuard = new ListNode(0);
        safeGuard.next = head;
        ListNode prev = safeGuard;
        while (prev.next != null && prev.next.next != null) {
            ListNode second = prev.next.next;
            ListNode first = prev.next;
            
            first.next = second.next;
            second.next = first;
            prev.next = second;
            prev = prev.next.next;
        }
        return safeGuard.next;
    }
}
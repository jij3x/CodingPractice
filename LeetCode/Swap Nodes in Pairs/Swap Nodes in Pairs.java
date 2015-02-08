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
        ListNode start = new ListNode(0);
        start.next = head;
        for (ListNode tail = start; tail.next != null && tail.next.next != null; tail = tail.next.next) {
            ListNode next = tail.next, nextNext = tail.next.next;
            next.next = nextNext.next;
            nextNext.next = next;
            tail.next = nextNext;
        }
        return start.next;
    }
}

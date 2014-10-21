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
        for (ListNode pivot = start; pivot.next != null && pivot.next.next != null; pivot = pivot.next.next) {
            ListNode node1 = pivot.next, node2 = pivot.next.next;
            node1.next = node2.next;
            node2.next = node1;
            pivot.next = node2;
        }
        return start.next;
    }
}

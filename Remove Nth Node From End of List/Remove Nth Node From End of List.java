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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0), pioneer = start, curr = start;
        start.next = head;
        for (int i = 0; i < n; i++)
            pioneer = pioneer.next;

        while (pioneer.next != null) {
            pioneer = pioneer.next;
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return start.next;
    }
}

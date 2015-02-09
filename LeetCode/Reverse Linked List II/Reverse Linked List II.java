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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode prev = start;
        for (int i = 1; i < m; i++)
            prev = prev.next;

        ListNode curr = prev.next;
        for (int i = m; i < n; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return start.next;
    }
}

class Solution2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode prev = start;
        for (int i = 1; i < m; i++)
            prev = prev.next;

        head = prev;
        ListNode tail = prev.next, next = prev.next;
        for (int i = m; i <= n; i++) {
            ListNode temp = next.next;
            next.next = head;
            head = next;
            next = temp;
        }

        prev.next = head;
        tail.next = next;
        return start.next;
    }
}

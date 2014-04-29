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
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null)
            return head;

        int length = 0;
        for (ListNode temp = head; temp != null; temp = temp.next)
            length++;
        n %= length;
        if (n == 0)
            return head;

        ListNode prev = head, tail = head.next;
        for (int i = 0; i < n - 1; i++)
            tail = tail.next;
        while (tail.next != null) {
            prev = prev.next;
            tail = tail.next;
        }

        tail.next = head;
        head = prev.next;
        prev.next = null;
        return head;
    }
}

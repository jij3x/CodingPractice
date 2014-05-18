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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode start = new ListNode(0), tail = start;

        while (head != null) {
            if (head.next == null || head.val != head.next.val) {
                tail.next = head;
                tail = tail.next;
            } else {
                while (head.next != null && head.val == head.next.val)
                    head.next = head.next.next;
            }
            head = head.next;
        }

        tail.next = null;
        return start.next;
    }
}

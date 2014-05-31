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
    public ListNode insertionSortList(ListNode head) {
        ListNode start = new ListNode(0);
        while (head != null) {
            ListNode prev = start, curr = head;
            head = head.next;
            while (prev.next != null && curr.val > prev.next.val)
                prev = prev.next;

            curr.next = prev.next;
            prev.next = curr;
        }
        return start.next;
    }
}

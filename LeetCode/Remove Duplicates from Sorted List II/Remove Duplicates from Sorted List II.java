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

        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val)
                    head = head.next;
            } else {
                tail.next = head;
                tail = head;
            }
            head = head.next;
        }

        tail.next = head;
        return start.next;
    }
}

class Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        ListNode p = head.next;
        if (p == null || p.val != head.val) {
            head.next = deleteDuplicates(p);
            return head;
        }
        while (p != null && p.val == head.val) {
            p = p.next;
        }
        return deleteDuplicates(p);
    }
}

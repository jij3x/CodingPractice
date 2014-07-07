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
    public ListNode reverseKGroup(ListNode head, int k) {
        int len = 0;
        for (ListNode p = head; p != null; p = p.next)
            len++;

        ListNode start = new ListNode(0), prev = start;
        start.next = head;
        for (int i = 0; i < len / k; i++) {
            for (int j = 1; j < k; j++) {
                ListNode next = head.next;
                head.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = head;
            head = head.next;
        }
        return start.next;
    }
}

class Solution2 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = new ListNode(0), nextHead = head, prev = start;
        start.next = head;
        while (nextHead != null) {
            ListNode segTail = nextHead, segHead = nextHead;
            nextHead = nextHead.next;
            int i = 1;
            while (i < k && nextHead != null) {
                i++;
                segHead = segHead.next;
                nextHead = nextHead.next;
            }

            if (i == k) {
                segHead.next = null;
                for (ListNode p = null, c = segTail, t = null; c != null;) {
                    t = c.next;
                    c.next = p;
                    p = c;
                    c = t;
                }
                prev.next = segHead;
                segTail.next = nextHead;
                prev = segTail;
            }
        }
        return start.next;
    }
}

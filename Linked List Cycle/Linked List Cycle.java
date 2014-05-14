/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        for (ListNode pioneer = head; pioneer != null && pioneer.next != null && pioneer.next.next != null;) {
            head = head.next;
            pioneer = pioneer.next.next;
            if (head == pioneer)
                return true;
        }
        return false;
    }
}

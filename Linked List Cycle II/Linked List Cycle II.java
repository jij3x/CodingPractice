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
    public ListNode detectCycle(ListNode head) {
        for (ListNode pioneer = head, pivot = head;
                pioneer != null && pioneer.next != null && pioneer.next.next != null;) {
            if ((pivot = pivot.next) == (pioneer = pioneer.next.next)) {
                while (head != pivot) {
                    head = head.next;
                    pivot = pivot.next;
                }
                return head;
            }
        }
        return null;
    }
}

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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;

        ListNode prev = head, head2 = head;
        while (head2.next != null && head2.next.next != null) {
            head2 = head2.next.next;
            prev = prev.next;
        }
        head2 = prev.next;
        prev.next = null;

        prev = null;
        while (head2 != null) {
            ListNode temp = head2;
            head2 = head2.next;
            temp.next = prev;
            prev = temp;
        }
        head2 = prev;
        
        while (head2 != null) {
            ListNode temp = head2;
            head2 = head2.next;
            temp.next = head.next;
            head.next = temp;
            head = temp.next;
        }
    }
}

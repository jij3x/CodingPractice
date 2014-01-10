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
        if (head == null)
            return null;
            
        ListNode fastCursor = head;
        ListNode slowCursor = head;
        
        while (fastCursor.next != null && fastCursor.next.next!=null) {
            fastCursor = fastCursor.next.next;
            slowCursor = slowCursor.next;
            
            if (fastCursor == slowCursor) {
                ListNode breakCursor = head;
                while (breakCursor != slowCursor) {
                    slowCursor = slowCursor.next;
                    breakCursor = breakCursor.next;
                }
                return breakCursor;
            }
        }

        return null;
    }
}
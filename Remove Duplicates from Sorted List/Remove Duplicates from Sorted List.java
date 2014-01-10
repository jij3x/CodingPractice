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
        if (head==null)
            return null;
        
        ListNode pivot = head;
        while (pivot.next != null) {
            if (pivot.next.val == pivot.val) {
                pivot.next = pivot.next.next;
            } else {
                pivot = pivot.next;
            }
        }
        
        return head;
    }
}
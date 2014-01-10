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
        if (head == null)
            return false;

        ListNode oneStepPivot = head;
        ListNode twoStepsPivot = head;
        
        while (twoStepsPivot.next!= null && twoStepsPivot.next.next!=null) {
            oneStepPivot = oneStepPivot.next;
            twoStepsPivot = twoStepsPivot.next.next;
            if (oneStepPivot == twoStepsPivot)
                return true;
        }
        return false;
    }
}
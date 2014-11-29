public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int aLength = 0, bLength = 0;
        for (ListNode p = headA; p != null; p = p.next)
            aLength++;
        for (ListNode p = headB; p != null; p = p.next)
            bLength++;

        while (aLength > bLength) {
            headA = headA.next;
            aLength--;
        }
        while (bLength > aLength) {
            headB = headB.next;
            bLength--;
        }

        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
}
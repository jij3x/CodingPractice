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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode list1 = head, list2 = head;
        while (list2.next != null && list2.next.next != null) {
            list1 = list1.next;
            list2 = list2.next.next;
        }
        list2 = list1.next;
        list1.next = null;
        list1 = head;

        list1 = sortList(list1);
        list2 = sortList(list2);

        ListNode start = new ListNode(0), prev = start;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 == null ? list2 : list1;

        return start.next;
    }
}

class Solution2 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode list1 = head, list2 = head, tail = head;
        for (ListNode p = list1; p != null; p = p == null ? null : p.next) {
            tail = list2;
            list2 = list2.next;
            p = p.next;
        }
        tail.next = null;

        list1 = sortList(list1);
        list2 = sortList(list2);

        ListNode start = new ListNode(0);
        for (ListNode prev = start; list1 != null || list2 != null; prev = prev.next) {
            boolean leftIn = (list2 == null || (list1 != null && list2 != null && list1.val < list2.val));
            prev.next = leftIn ? list1 : list2;
            list1 = leftIn ? list1.next : list1;
            list2 = leftIn ? list2 : list2.next;
        }
        return start.next;
    }
}

class Solution3 {
    public ListNode sortList(ListNode head) {
        ListNode start = new ListNode(0);
        start.next = head;

        int length = 0;
        for (; head != null; length++)
            head = head.next;

        if (length > 1) {
            int segment = 1;
            while (length > segment) {
                ListNode prev = start;
                while (prev.next != null) {
                    ListNode list1 = prev.next;
                    ListNode list2 = cutList(list1, segment);
                    ListNode rest = cutList(list2, segment);

                    ListNode[] headAndTail = new ListNode[2];
                    mergeList(list1, list2, headAndTail);
                    prev.next = headAndTail[0];
                    prev = headAndTail[1];
                    prev.next = rest;
                }
                segment *= 2;
            }
        }
        return start.next;
    }

    private ListNode cutList(ListNode head, int n) {
        if (head == null)
            return null;

        for (int i = 1; i < n && head.next != null; i++)
            head = head.next;
        ListNode tail = head.next;
        head.next = null;
        return tail;
    }

    private void mergeList(ListNode list1, ListNode list2, ListNode[] headAndTail) {
        ListNode start = new ListNode(0);
        headAndTail[1] = start;
        for (; list1 != null || list2 != null; headAndTail[1] = headAndTail[1].next) {
            boolean list1In = (list2 == null || (list1 != null && list2 != null && list1.val < list2.val));
            headAndTail[1].next = list1In ? list1 : list2;
            list1 = list1In ? list1.next : list1;
            list2 = list1In ? list2 : list2.next;
        }
        headAndTail[0] = start.next;
    }
}

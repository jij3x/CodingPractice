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
    public ListNode mergeKLists(List<ListNode> lists) {
        for (int end = lists.size() - 1; end > 0; end /= 2) {
            for (int i = end / 2 + 1, j = 0; i <= end; i++, j++) {
                ListNode start = new ListNode(0), tail = start;
                ListNode l1 = lists.get(i), l2 = lists.get(j);
                while (l1 != null && l2 != null) {
                    if (l1.val < l2.val) {
                        tail.next = l1;
                        l1 = l1.next;
                    } else {
                        tail.next = l2;
                        l2 = l2.next;
                    }
                    tail = tail.next;
                }
                tail.next = l1 == null ? l2 : l1;

                lists.set(j, start.next);
            }
        }
        return lists.isEmpty() ? null : lists.get(0);
    }
}

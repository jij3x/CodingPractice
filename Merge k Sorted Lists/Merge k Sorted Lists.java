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
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        for (int cnt = lists.size(); cnt >= 2; cnt = cnt / 2 + cnt % 2) {
            for (int i = 0; i < cnt / 2; i++) {
                ListNode start = new ListNode(0);
                ListNode tail = start, list1 = lists.get(i), list2 = lists.get(cnt - 1 - i);
                while (list1 != null && list2 != null) {
                    if (list1.val < list2.val) {
                        tail.next = list1;
                        list1 = list1.next;
                    } else {
                        tail.next = list2;
                        list2 = list2.next;
                    }
                    tail = tail.next;
                }
                tail.next = list1 == null ? list2 : list1;

                lists.set(i, start.next);
            }
        }
        return lists.size() == 0 ? null : lists.get(0);
    }
}

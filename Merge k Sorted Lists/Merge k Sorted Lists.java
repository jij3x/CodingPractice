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
		int length = lists.size();
		if (length == 0)
			return null;

		while (length > 1) {
			for (int i = 0; i < length / 2; i++) {
				ListNode head = mergeList(lists.get(i), lists.get(length - 1 - i));
				lists.set(i, head);
			}
			length = (length / 2) + (length % 2);
		}

		return lists.get(0);
	}

	private ListNode mergeList(ListNode list1, ListNode list2) {
		ListNode start = new ListNode(0);
		ListNode tail = start;
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

		return start.next;
	}
}

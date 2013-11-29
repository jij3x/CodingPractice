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
		ListNode start = new ListNode(0);
		start.next = head;

		int length = 0;
		while (head != null) {
			head = head.next;
			length++;
		}
		if (length < 2)
			return start.next;

		int segment = 1;
		while (length > segment) {
			ListNode prevEnd = start;
			while (prevEnd.next != null) {
				ListNode list1 = prevEnd.next;
				ListNode list2 = cutList(list1, segment);
				ListNode listLeft = cutList(list2, segment);

				ArrayList<ListNode> headAndEnd = mergeList(list1, list2);
				prevEnd.next = headAndEnd.get(0);
				prevEnd = headAndEnd.get(1);
				prevEnd.next = listLeft;
			}
			segment *= 2;
		}

		return start.next;
	}

	/*
	 * return cutted list's head
	 */
	private ListNode cutList(ListNode head, int n) {
		if (head == null)
			return null;

		ListNode start = new ListNode(0);
		start.next = head;

		ListNode end = start;
		for (int i = 0; i < n; i++) {
			end = end.next;
			if (end.next == null)
				break;
		}

		ListNode cuttedList = end.next;
		end.next = null;

		return cuttedList;
	}

	/*
	 * return[0] - new list head; return[1] - new list end;
	 */
	private ArrayList<ListNode> mergeList(ListNode list1, ListNode list2) {
		ListNode start = new ListNode(0);
		ListNode end = start;
		while (list1 != null && list2 != null) {
			if (list1.val > list2.val) {
				end.next = list2;
				list2 = list2.next;
			} else {
				end.next = list1;
				list1 = list1.next;
			}
			end = end.next;
		}

		end.next = list1 == null ? list2 : list1;
		while (end.next != null)
			end = end.next;

		ArrayList<ListNode> result = new ArrayList<ListNode>();
		result.add(start.next);
		result.add(end);
		return result;
	}
}

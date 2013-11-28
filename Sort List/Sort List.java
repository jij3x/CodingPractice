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
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.

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

		ListNode listLeft = end.next;
		end.next = null;

		return listLeft;
	}

	private ArrayList<ListNode> mergeList(ListNode l1, ListNode l2) {
		ListNode start = new ListNode(0);
		ListNode end = start;
		while (l1 != null && l2 != null) {
			if (l1.val > l2.val) {
				end.next = l2;
				l2 = l2.next;
			} else {
				end.next = l1;
				l1 = l1.next;
			}
			end = end.next;
		}

		end.next = l1 == null ? l2 : l1;
		while (end.next != null)
			end = end.next;

		ArrayList<ListNode> result = new ArrayList<ListNode>();
		result.add(start.next);
		result.add(end);
		return result;
	}
}

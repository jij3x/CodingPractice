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

		ListNode rightHead = head;
		ListNode leftTail = head;
		ListNode pioneer = head;
		while (pioneer != null) {
			leftTail = rightHead;
			rightHead = rightHead.next;
			pioneer = pioneer.next;
			if (pioneer != null)
				pioneer = pioneer.next;
		}
		leftTail.next = null;

		head = sortList(head);
		rightHead = sortList(rightHead);

		ListNode start = new ListNode(0);
		start.next = head;
		ListNode prev = start;
		while (head != null && rightHead != null) {
			if (head.val > rightHead.val) {
				prev.next = head;
				head = head.next;
			} else {
				prev.next = rightHead;
				rightHead = rightHead.next;
			}
			prev = prev.next;
		}
		if (head == null)
			prev.next = rightHead;
		else
			prev.next = head;

		return start.next;
	}

	public ListNode sortList2(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode rightHead = head, leftTail = head;
		for (ListNode p = head; p != null; p = p == null ? null : p.next) {
			leftTail = rightHead;
			rightHead = rightHead.next;
			p = p.next;
		}
		leftTail.next = null;

		head = sortList(head);
		rightHead = sortList(rightHead);

		ListNode start = new ListNode(0);
		for (ListNode prev = start; head != null || rightHead != null; prev = prev.next) {
			boolean leftIn = (rightHead == null || (head != null && rightHead != null && head.val < rightHead.val));
			prev.next = leftIn ? head : rightHead;
			head = leftIn ? head.next : head;
			rightHead = leftIn ? rightHead : rightHead.next;
		}
		return start.next;
	}
}

class Solution2 {
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

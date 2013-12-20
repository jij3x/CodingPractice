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
		start.next = head;
		for (ListNode prev = start; head != null || rightHead != null; prev = prev.next) {
			boolean leftIn = (rightHead == null || (head != null && rightHead != null && head.val < rightHead.val));
			prev.next = leftIn ? head : rightHead;
			head = leftIn ? head.next : head;
			rightHead = leftIn ? rightHead : rightHead.next;
		}
		return start.next;
	}
}

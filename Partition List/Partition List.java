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
	public ListNode partition(ListNode head, int x) {
		ListNode greater = new ListNode(0), greaterTail = greater;
		ListNode less = new ListNode(0), lessTail = less;

		less.next = head;
		while (head != null) {
			if (head.val >= x) {
				lessTail.next = head.next;
				greaterTail.next = head;
				greaterTail = head;
				greaterTail.next = null;
				head = lessTail.next;
			} else {
				lessTail = head;
				head = head.next;
			}
		}
		lessTail.next = greater.next;
		return less.next;
	}
}

class Solution2 {
	public ListNode partition(ListNode head, int x) {
		ListNode greater = new ListNode(0), greaterTail = greater;
		ListNode less = new ListNode(0), lessTail = less;

		while (head != null) {
			if (head.val < x) {
				lessTail.next = head;
				lessTail = head;
			} else {
				greaterTail.next = head;
				greaterTail = head;
			}
			head = head.next;
			lessTail.next = null;
			greaterTail.next = null;
		}

		lessTail.next = greater.next;
		return less.next;
	}
}

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
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode start = new ListNode(0);
		start.next = head;
		ListNode prev = start;
		for (int i = 1; i < m; i++)
			prev = prev.next;

		ListNode curr = prev.next;
		for (int i = m; i < n; i++) {
			ListNode next = curr.next;
			curr.next = next.next;
			next.next = prev.next;
			prev.next = next;
		}

		return start.next;
	}
}

class Solution2 {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode start = new ListNode(0);
		start.next = head;
		ListNode prev = start;
		for (int i = 1; i < m; i++)
			prev = prev.next;

		ListNode subTail = prev.next, subHead = prev, next = prev.next;
		for (int i = m; i <= n; i++) {
			ListNode temp = next.next;
			next.next = subHead;
			subHead = next;
			next = temp;
		}

		prev.next = subHead;
		subTail.next = next;
		return start.next;
	}
}

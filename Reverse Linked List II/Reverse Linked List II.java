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

		for (int i = 1; i < m && prev.next != null; i++)
			prev = prev.next;

		ListNode pivot = prev.next;
		for (int i = m; i < n && pivot.next != null; i++) {
			ListNode second = pivot.next;
			pivot.next = second.next;
			second.next = prev.next;
			prev.next = second;
		}
		
		return start.next;
	}
}
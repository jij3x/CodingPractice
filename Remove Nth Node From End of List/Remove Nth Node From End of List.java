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
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode start = new ListNode(0), prev = start, pivot = head, pioneer = head;
		start.next = head;

		for (int i = 0; i < n; i++)
			pioneer = pioneer.next;

		while (pioneer != null) {
			pioneer = pioneer.next;
			pivot = pivot.next;
			prev = prev.next;
		}
		
		prev.next = pivot.next;
		return start.next;
	}
}
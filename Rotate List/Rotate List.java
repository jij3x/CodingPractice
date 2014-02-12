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
	public ListNode rotateRight(ListNode head, int n) {
		if (head == null)
			return null;

		int length = 0;
		for (ListNode temp = head; temp != null; temp = temp.next)
			length++;
		if (n == length)
			return head;
		n %= length;

		ListNode tail = head, pinoeer = head;
		for (int i = 0; i < n; i++)
			pinoeer = pinoeer.next;
		while (pinoeer.next != null) {
			pinoeer = pinoeer.next;
			tail = tail.next;
		}

		pinoeer.next = head;
		head = tail.next;
		tail.next = null;
		return head;
	}
}
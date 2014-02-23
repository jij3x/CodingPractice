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
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode start = new ListNode(0);
		start.next = head;

		int length = 0;
		for (ListNode pivot = head; pivot != null; length++)
			pivot = pivot.next;

		ListNode prev = start;
		for (int i = 0; i < length / k; i++) {
			for (int j = 1; j < k; j++) {
				ListNode next = head.next;
				head.next = next.next;
				next.next = prev.next;
				prev.next = next;
			}
			prev = head;
			head = head.next;
		}

		return start.next;
	}
}

class Solution2 {
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode start = new ListNode(0), prev = start, pivot = start;
		start.next = head;

		done: while (true) {
			pivot = prev;
			for (int count = 0; count < k; count++) {
				pivot = pivot.next;
				if (pivot == null)
					break done;
			}

			ListNode curr = prev.next;
			for (int i = 1; i < k; i++) {
				ListNode next = curr.next;
				curr.next = next.next;
				next.next = prev.next;
				prev.next = next;
			}
			prev = curr;
		}

		return start.next;
	}
}

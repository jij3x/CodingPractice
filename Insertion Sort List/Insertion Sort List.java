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
	public ListNode insertionSortList(ListNode head) {
		ListNode safeGuard = new ListNode(0);
		while (head != null) {
			ListNode pivot = safeGuard;
			ListNode curr = head;
			head = head.next;
			while (pivot.next != null && curr.val > pivot.next.val)
				pivot = pivot.next;

			curr.next = pivot.next;
			pivot.next = curr;
		}

		return safeGuard.next;
	}
}

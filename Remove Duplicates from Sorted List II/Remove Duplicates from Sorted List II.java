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
	public ListNode deleteDuplicates(ListNode head) {
		ListNode safeGuard = new ListNode(0);
		safeGuard.next = head;
		ListNode prev = safeGuard;

		ListNode pivot = prev.next;
		while (pivot != null) {
			while (pivot.next != null && pivot.val == pivot.next.val)
				pivot = pivot.next;

			if (prev.next == pivot) {
				prev = pivot;
			} else {
				prev.next = pivot.next;
			}
			pivot = pivot.next;
		}
		return safeGuard.next;
	}
}
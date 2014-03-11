/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	private ListNode currentNode;

	public TreeNode sortedListToBST(ListNode head) {
		this.currentNode = head;
		int length = 0;
		for (; head != null; head = head.next)
			length++;

		return doSortedListToBST(length);
	}

	private TreeNode doSortedListToBST(int length) {
		if (length <= 0)
			return null;

		TreeNode left = doSortedListToBST(length / 2);
		TreeNode root = new TreeNode(this.currentNode.val);
		root.left = left;
		this.currentNode = this.currentNode.next;
		root.right = doSortedListToBST(length - length / 2 - 1);
		return root;
	}
}
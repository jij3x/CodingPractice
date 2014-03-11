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
	public TreeNode sortedArrayToBST(int[] num) {
		return doSortedArrayToBST(num, 0, num.length - 1);
	}

	private TreeNode doSortedArrayToBST(int[] num, int start, int end) {
		if (start > end)
			return null;

		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = doSortedArrayToBST(num, start, mid - 1);
		root.right = doSortedArrayToBST(num, mid + 1, end);
		return root;
	}
}
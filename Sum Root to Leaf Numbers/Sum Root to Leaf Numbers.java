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
	public int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;

		int[] result = new int[1];
		dfs(root, 0, result);
		return result[0];
	}

	private void dfs(TreeNode root, int past, int[] result) {
		if (root.left == null && root.right == null) {
			result[0] += past * 10 + root.val;
			return;
		}

		if (root.left != null)
			dfs(root.left, past * 10 + root.val, result);
		if (root.right != null)
			dfs(root.right, past * 10 + root.val, result);
	}
}
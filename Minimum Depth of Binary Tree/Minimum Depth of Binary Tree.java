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
	private int minDepth;

	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;

		minDepth = Integer.MAX_VALUE;
		dfs(root, 0);
		return minDepth;
	}

	private void dfs(TreeNode root, int depth) {
		if (root.left == null && root.right == null) {
			minDepth = Math.min(minDepth, depth + 1);
			return;
		}

		if (root.left != null)
			dfs(root.left, depth + 1);
		if (root.right != null)
			dfs(root.right, depth + 1);
	}
}

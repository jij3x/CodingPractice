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
	private int maxDepth;

	public int maxDepth(TreeNode root) {
		maxDepth = 0;
		dfs(root, 0);
		return maxDepth;
	}

	private void dfs(TreeNode root, int depth) {
		if (root == null) {
			maxDepth = Math.max(maxDepth, depth);
			return;
		}

		depth++;
		dfs(root.left, depth);
		dfs(root.right, depth);
	}
}

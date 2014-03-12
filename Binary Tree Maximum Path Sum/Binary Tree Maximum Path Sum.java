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
	private int max;

	public int maxPathSum(TreeNode root) {
		max = root == null ? 0 : root.val;
		dfsMaxPathSum(root);
		return max;
	}

	private int dfsMaxPathSum(TreeNode root) {
		if (root == null)
			return 0;

		int lMax = dfsMaxPathSum(root.left);
		int rMax = dfsMaxPathSum(root.right);
		max = Math.max(Math.max(max, lMax + root.val + rMax), Math.max(root.val + lMax, root.val + rMax));
		return Math.max(root.val, Math.max(root.val + lMax, root.val + rMax));
	}
}
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
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;

		return hasSum(root, sum, 0);
	}

	private boolean hasSum(TreeNode root, int target, int sum) {
		sum += root.val;
		if (root.left == null && root.right == null) {
			if (sum == target)
				return true;
			else
				return false;
		}

		if (root.left != null && root.right == null)
			return hasSum(root.left, target, sum);

		if (root.right != null && root.left == null)
			return hasSum(root.right, target, sum);

		return hasSum(root.left, target, sum) || hasSum(root.right, target, sum);
	}
}
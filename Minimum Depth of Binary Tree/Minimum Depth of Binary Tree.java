public class Solution {
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		
		int[] minDepth = new int[1];
		minDepth[0] = Integer.MAX_VALUE;
		dfs(root, 0, minDepth);
		return minDepth[0];
	}

	private void dfs(TreeNode root, int depth, int[] minDepth) {
		if (root.left == null && root.right == null) {
			minDepth[0] = Math.min(minDepth[0], depth + 1);
			return;
		}

		if (root.left != null)
			dfs(root.left, depth + 1, minDepth);
		if (root.right != null)
			dfs(root.right, depth + 1, minDepth);
	}
}
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
	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return result;

		dfs(root, sum, new ArrayList<Integer>(), result);
		return result;
	}

	private void dfs(TreeNode root, int target, ArrayList<Integer> past, ArrayList<ArrayList<Integer>> result) {
		past.add(root.val);
		if (root.left == null && root.right == null) {
			int sum = 0;
			for (Integer i : past)
				sum += i;
			if (sum == target)
				result.add(new ArrayList<Integer>(past));
			past.remove(past.size() - 1);
			return;
		}

		if (root.left != null)
			dfs(root.left, target, past, result);
		if (root.right != null)
			dfs(root.right, target, past, result);

		past.remove(past.size() - 1);
		return;
	}
}
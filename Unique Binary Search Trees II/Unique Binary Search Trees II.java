/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class Solution {
	public ArrayList<TreeNode> generateTrees(int n) {
		return doGenerateTrees(1, n);
	}

	private ArrayList<TreeNode> doGenerateTrees(int start, int end) {
		ArrayList<TreeNode> result = new ArrayList<TreeNode>();
		if (start > end) {
			result.add(null);
			return result;
		}

		for (int i = start; i <= end; i++) {
			ArrayList<TreeNode> leftTrees = doGenerateTrees(start, i - 1);
			ArrayList<TreeNode> rightTrees = doGenerateTrees(i + 1, end);

			for (int l = 0; l < leftTrees.size(); l++) {
				for (int r = 0; r < rightTrees.size(); r++) {
					TreeNode root = new TreeNode(i);
					root.left = leftTrees.get(l); // Need deep copy?
					root.right = rightTrees.get(r); // Need deep copy?
					result.add(root);
				}
			}
		}
		return result;
	}
}

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
	public void flatten(TreeNode root) {
		if (root == null)
			return;
		dfs(root);
	}

	private TreeNode dfs(TreeNode root) {
		if (root.left == null && root.right == null) {
			return root;
		} else if (root.left == null) {
			return dfs(root.right);
		} else if (root.right == null) {
			root.right = root.left;
			root.left = null;
			return dfs(root.right);
		}

		TreeNode leftTail = dfs(root.left);
		TreeNode rightTail = dfs(root.right);
		leftTail.right = root.right;
		root.right = root.left;
		root.left = null;
		return rightTail;
	}
}

class Solution2 {
	public void flatten(TreeNode root) {
		if (root == null)
			return;

		if (root.left != null) {
			TreeNode rightMost = root.left;
			while (rightMost.right != null)
				rightMost = rightMost.right;

			rightMost.right = root.right;
			root.right = root.left;
			root.left = null;
		}

		flatten(root.right);
	}
}

class Solution3 {
	public void flatten(TreeNode root) {
		TreeNode[] lastVisited = new TreeNode[1];
		postOrderTraversal(root, lastVisited);
	}

	private void postOrderTraversal(TreeNode root, TreeNode[] lastVisited) {
		if (root == null)
			return;
		
		postOrderTraversal(root.right, lastVisited);
		postOrderTraversal(root.left, lastVisited);
		
		root.right = lastVisited[0];
		root.left = null;
		
		lastVisited[0] = root;
	}
}

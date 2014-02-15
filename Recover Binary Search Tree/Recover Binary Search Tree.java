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
	TreeNode prev, node1, node2;
	boolean errorDetected;

	public void recoverTree(TreeNode root) {
		errorDetected = false;
		inOrderTraversal(root);

		int temp = node1.val;
		node1.val = node2.val;
		node2.val = temp;
	}

	private void inOrderTraversal(TreeNode root) {
		if (root == null)
			return;

		inOrderTraversal(root.left);

		if (prev != null && root.val < prev.val) {
			if (errorDetected) {
				node2 = root;
			} else {
				node1 = prev;
				node2 = root;
				errorDetected = true;
			}
		}
		prev = root;

		inOrderTraversal(root.right);
	}
}

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
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		return doBuildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
	}

	private TreeNode doBuildTree(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
		if (is > ie)
			return null;

		int rootVal = postorder[pe], leftCount = 0;
		for (int i = is; i <= ie; i++) {
			if (inorder[i] == rootVal) {
				leftCount = i - is;
				break;
			}
		}

		TreeNode root = new TreeNode(rootVal);
		root.left = doBuildTree(inorder, is, is + leftCount - 1, postorder, ps, ps + leftCount - 1);
		root.right = doBuildTree(inorder, is + leftCount + 1, ie, postorder, ps + leftCount, pe - 1);
		return root;
	}
}
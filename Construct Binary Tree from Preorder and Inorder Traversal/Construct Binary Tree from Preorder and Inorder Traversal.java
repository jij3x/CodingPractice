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
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return doBuildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}

	private TreeNode doBuildTree(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
		if (ps > pe)
			return null;

		int rootVal = preorder[ps], leftCount = 0;
		for (int i = is; i <= ie; i++) {
			if (inorder[i] == rootVal) {
				leftCount = i - is;
				break;
			}
		}

		TreeNode root = new TreeNode(rootVal);
		root.left = doBuildTree(preorder, ps + 1, ps + leftCount, inorder, is, is + leftCount - 1);
		root.right = doBuildTree(preorder, ps + leftCount + 1, pe, inorder, is + leftCount + 1, ie);
		return root;
	}
}
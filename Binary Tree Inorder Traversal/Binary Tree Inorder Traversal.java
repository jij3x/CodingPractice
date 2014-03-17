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
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> parentStack = new Stack<TreeNode>();
		TreeNode curr = root;
		while (!parentStack.isEmpty() || curr != null) {
			if (curr != null) {
				parentStack.push(curr);
				curr = curr.left;
			} else {
				curr = parentStack.pop();
				result.add(curr.val);
				curr = curr.right;
			}
		}
		return result;
	}
}

class Solution2 {
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		doInorderTraversal(root, result);

		return result;
	}

	private void doInorderTraversal(TreeNode root, ArrayList<Integer> result) {
		if (root == null)
			return;

		doInorderTraversal(root.left, result);
		result.add(root.val);
		doInorderTraversal(root.right, result);
	}
}

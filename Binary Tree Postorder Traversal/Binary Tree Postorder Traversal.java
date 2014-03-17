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
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> parentStack = new Stack<TreeNode>();
		TreeNode curr = root, prev = null;
		while (!parentStack.isEmpty() || curr != null) {
			if (curr != null) {
				parentStack.push(curr);
				curr = curr.left;
			} else {
				if (parentStack.peek().right != null && parentStack.peek().right != prev) {
					curr = parentStack.peek().right;
				} else {
					prev = parentStack.pop();
					result.add(prev.val);
				}
			}
		}
		return result;
	}
}

class Solution2 {
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		doPostorderTraversal(root, result);

		return result;
	}

	private void doPostorderTraversal(TreeNode root, ArrayList<Integer> result) {
		if (root == null)
			return;

		doPostorderTraversal(root.left, result);
		doPostorderTraversal(root.right, result);
		result.add(root.val);
	}
}

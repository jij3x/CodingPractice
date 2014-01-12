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
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return result;

		Stack<ArrayList<Integer>> buffer = new Stack<ArrayList<Integer>>();
		Queue<TreeNode> currentLevel = new LinkedList<TreeNode>();
		Queue<TreeNode> nextLevel = new LinkedList<TreeNode>();

		ArrayList<Integer> row = new ArrayList<Integer>();
		currentLevel.add(root);
		while (!currentLevel.isEmpty()) {
			TreeNode currentNode = currentLevel.poll();
			row.add(currentNode.val);

			if (currentNode.left != null)
				nextLevel.add(currentNode.left);
			if (currentNode.right != null)
				nextLevel.add(currentNode.right);

			if (currentLevel.isEmpty()) {
				buffer.push(row);
				row = new ArrayList<Integer>();
				currentLevel = nextLevel;
				nextLevel = new LinkedList<TreeNode>();
			}
		}

		while (!buffer.empty())
			result.add(buffer.pop());

		return result;
	}
}

class Solution2 {
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		dfs(root, 0, result);
		Collections.reverse(result);
		return result;
	}

	private void dfs(TreeNode root, int level, ArrayList<ArrayList<Integer>> result) {
		if (root == null)
			return;

		if (level > result.size() - 1)
			result.add(new ArrayList<Integer>());
		result.get(level).add(root.val);

		dfs(root.left, level + 1, result);
		dfs(root.right, level + 1, result);
	}
}
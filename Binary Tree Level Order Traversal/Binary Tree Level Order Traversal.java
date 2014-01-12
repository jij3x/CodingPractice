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
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return result;

		Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
		Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
		Queue<TreeNode> currentLevel = queue1;
		Queue<TreeNode> nextLevel = queue2;

		currentLevel.add(root);
		ArrayList<Integer> row = new ArrayList<Integer>();
		while (!currentLevel.isEmpty()) {
			TreeNode currentNode = currentLevel.poll();
			row.add(currentNode.val);

			if (currentNode.left != null)
				nextLevel.add(currentNode.left);
			if (currentNode.right != null)
				nextLevel.add(currentNode.right);

			if (currentLevel.isEmpty()) {
				result.add(row);
				row = new ArrayList<Integer>();
				currentLevel = currentLevel == queue1 ? queue2 : queue1;
				nextLevel = nextLevel == queue1 ? queue2 : queue1;
			}
		}

		return result;
	}
}

class Solution2 {
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return result;

		Queue<TreeNode> workingQue = new LinkedList<TreeNode>();
		int nodesInCurrentLevel = 1;
		int nodesInNextLevel = 0;
		workingQue.add(root);

		ArrayList<Integer> row = new ArrayList<Integer>();
		while (!workingQue.isEmpty()) {
			TreeNode currentNode = workingQue.poll();
			nodesInCurrentLevel--;
			row.add(currentNode.val);

			if (currentNode.left != null) {
				workingQue.add(currentNode.left);
				nodesInNextLevel++;
			}
			if (currentNode.right != null) {
				workingQue.add(currentNode.right);
				nodesInNextLevel++;
			}

			if (nodesInCurrentLevel == 0) {
				result.add(row);
				row = new ArrayList<Integer>();
				nodesInCurrentLevel = nodesInNextLevel;
				nodesInNextLevel = 0;
			}
		}

		return result;
	}
}

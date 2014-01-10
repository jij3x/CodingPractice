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
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return output;

		Stack<TreeNode> level1 = new Stack<TreeNode>();
		Stack<TreeNode> level2 = new Stack<TreeNode>();
		Stack<TreeNode> currentLevel = level1, nextLevel = level2;

		ArrayList<Integer> lineOutput = new ArrayList<Integer>();
		currentLevel.push(root);
		while (!currentLevel.empty()) {
			TreeNode node = currentLevel.pop();
			lineOutput.add(node.val);

			if (currentLevel == level1) { // go from left to right
				if (node.left != null)
					nextLevel.push(node.left);
				if (node.right != null)
					nextLevel.push(node.right);
			} else { // go from right to left
				if (node.right != null)
					nextLevel.push(node.right);
				if (node.left != null)
					nextLevel.push(node.left);
			}

			if (currentLevel.empty()) {
				output.add(lineOutput);
				lineOutput = new ArrayList<Integer>();

				currentLevel = currentLevel == level1 ? level2 : level1;
				nextLevel = nextLevel == level1 ? level2 : level1;
			}
		}
		return output;
	}
}
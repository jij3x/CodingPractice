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
    public boolean isBalanced(TreeNode root) {
        int[] height = new int[1];
        return doIsBalanced(root, height);
    }
    
	private boolean doIsBalanced(TreeNode root, int[] height) {
		if (root == null) {
		    height[0] = 0;
			return true;
		}
		
		boolean leftChildBalanced = doIsBalanced(root.left, height);
		int leftHeight = height[0];
		
		boolean rightChildBalanced = doIsBalanced(root.right, height);
		int rightHeight = height[0];
		
		if (!leftChildBalanced || !rightChildBalanced)
		    return false;
		    
		if (Math.abs(leftHeight-rightHeight) > 1)
		    return false;
		
		height[0] = Math.max(leftHeight, rightHeight) + 1;
		return true;
	}
}
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
    private int height;

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            height = 0;
            return true;
        }

        if (!isBalanced(root.left))
            return false;
        int leftHeight = height;

        if (!isBalanced(root.right))
            return false;
        int rightHeight = height;

        height = Math.max(leftHeight, rightHeight) + 1;
        return Math.abs(leftHeight - rightHeight) <= 1;

    }
}

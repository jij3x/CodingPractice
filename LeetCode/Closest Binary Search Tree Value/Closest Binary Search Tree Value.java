/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int closestValue(TreeNode root, double target) {
        TreeNode child = root.val < target ? root.right : root.left;
        if (child == null)
            return root.val;
        int candidate = closestValue(child, target);
        return (Math.abs(root.val - target) < Math.abs(candidate - target)) ? root.val : candidate;
    }
}
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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        return doHasPathSum(root, 0, sum);
    }

    private boolean doHasPathSum(TreeNode root, int sum, int target) {
        if (root.left == null && root.right == null)
            return sum + root.val == target;

        boolean leftHas = root.left == null ? false : doHasPathSum(root.left, sum + root.val, target);
        boolean rightHas = root.right == null ? false : doHasPathSum(root.right, sum + root.val, target);
        return leftHas || rightHas;
    }
}

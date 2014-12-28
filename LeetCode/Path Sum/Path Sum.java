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
        return doHasPathSum(root, sum);
    }

    private boolean doHasPathSum(TreeNode root, int sum) {
        if (root.left == null && root.right == null)
            return root.val == sum;

        boolean leftHas = root.left == null ? false : doHasPathSum(root.left, sum - root.val);
        boolean rightHas = root.right == null ? false : doHasPathSum(root.right, sum - root.val);
        return leftHas || rightHas;
    }
}

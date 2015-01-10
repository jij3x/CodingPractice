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
    private int max;

    public int maxPathSum(TreeNode root) {
        max = root.val;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;

        int lMax = dfs(root.left);
        int rMax = dfs(root.right);
        max = Math.max(Math.max(max, lMax + rMax + root.val), Math.max(lMax + root.val, rMax + root.val));
        max = Math.max(max, root.val);
        return Math.max(root.val, Math.max(root.val + lMax, root.val + rMax));
    }
}

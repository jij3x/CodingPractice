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

        int lMax = Math.max(0, dfs(root.left));
        int rMax = Math.max(0, dfs(root.right));
        max = Math.max(max, root.val + lMax + rMax);
        return root.val + Math.max(lMax, rMax);
    }
}

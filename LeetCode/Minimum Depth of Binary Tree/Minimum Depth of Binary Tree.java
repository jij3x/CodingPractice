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
    private int min;

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        min = Integer.MAX_VALUE;
        dfs(root, 1);
        return min;
    }

    private void dfs(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            min = Math.min(min, depth);
            return;
        }

        if (root.left != null)
            dfs(root.left, depth + 1);
        if (root.right != null)
            dfs(root.right, depth + 1);
    }
}

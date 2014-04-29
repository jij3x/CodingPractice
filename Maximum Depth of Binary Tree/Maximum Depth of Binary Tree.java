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

    public int maxDepth(TreeNode root) {
        max = 0;
        dfs(root, 0);
        return max;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            max = Math.max(max, depth);
            return;
        }

        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}

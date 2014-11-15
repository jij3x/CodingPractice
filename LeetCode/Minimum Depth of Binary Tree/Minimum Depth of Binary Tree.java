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
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null)
            return minDepth(root.left) + 1;

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}

class Solution2 {
    private int min;

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        min = Integer.MAX_VALUE;
        dfs(root, 1);
        return min;
    }

    private void dfs(TreeNode root, int level) {
        if (root.left != null)
            dfs(root.left, level + 1);
        if (root.right != null)
            dfs(root.right, level + 1);
        if (root.left == null && root.right == null)
            min = Math.min(min, level);
    }
}

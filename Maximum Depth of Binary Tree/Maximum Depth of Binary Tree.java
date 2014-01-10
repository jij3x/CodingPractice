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
    public int maxDepth(TreeNode root) {
        int[] maxDepth = new int[1];
        dfs(root, maxDepth, 0);
        return maxDepth[0];
    }
    
    private void dfs(TreeNode root, int[] maxDepth, int depth) {
        if (root == null) {
            maxDepth[0] = Math.max(maxDepth[0], depth);
            return;
        }
        
        depth++;
        dfs(root.left, maxDepth, depth);
        dfs(root.right, maxDepth, depth);
    }
}
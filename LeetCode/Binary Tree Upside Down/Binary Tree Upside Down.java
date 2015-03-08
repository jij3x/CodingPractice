public class Solution {
    public TreeNode UpsideDownBinaryTree(TreeNode root) {
        return dfs(root, null);
    }

    private TreeNode dfs(TreeNode root, TreeNode parent) {
        if (root == null)
            return parent;
        TreeNode newRoot = dfs(root.left, root);
        root.left = parent == null ? null : parent.right;
        root.right = parent;
        return newRoot;
    }
}
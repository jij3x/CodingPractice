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

class Solution1 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return root;

        TreeNode newRoot = upsideDownBinaryTree(root.left), p = newRoot;
        while (p.right != null)
            p = p.right;
        p.right = root;
        p.left = root.right;
        root.right = root.left = null;

        return newRoot;
    }
}

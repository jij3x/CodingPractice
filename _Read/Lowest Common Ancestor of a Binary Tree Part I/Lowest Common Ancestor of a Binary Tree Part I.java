public class Solution {
    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (root.val == p.val || root.val == q.val)
            return root;

        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }
}
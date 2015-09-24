public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            for (p = p.right; p.left != null;)
                p = p.left;
            return p;
        }
        TreeNode prev = null;
        while (root != p)
            root = p.val < root.val ? (prev = root).left : root.right;
        return prev;
    }
}
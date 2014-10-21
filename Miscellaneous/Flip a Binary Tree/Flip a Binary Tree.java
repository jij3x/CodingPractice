public class Solution {
    public TreeNode flipTree(TreeNode root) {
        TreeNode prev = null, prevRight = null;
        while (root != null) {
            TreeNode next = root.left, tempRight = root.right;

            root.right = prev;
            root.left = prevRight;

            prev = root;
            prevRight = tempRight;

            root = next;
        }
        return prev;
    }
}

class Solution2 {
    public TreeNode flipTree(TreeNode root) {
        return doFlipTree(root, null, null);
    }

    private TreeNode doFlipTree(TreeNode root, TreeNode prev, TreeNode prevRight) {
        if (root == null)
            return prev;

        TreeNode curr = root.left;
        TreeNode currRight = root.right;
        root.left = prevRight;
        root.right = prev;
        return doFlipTree(curr, root, currRight);
    }
}

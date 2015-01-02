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
    public void flatten(TreeNode root) {
        if (root == null)
            return;

        dfsFlatten(root);
    }

    private TreeNode dfsFlatten(TreeNode root) {
        if (root.left == null && root.right == null)
            return root;
        if (root.left == null)
            return dfsFlatten(root.right);
        if (root.right == null) {
            root.right = root.left;
            root.left = null;
            return dfsFlatten(root.right);
        }

        TreeNode leftTail = dfsFlatten(root.left);
        TreeNode rightTail = dfsFlatten(root.right);
        leftTail.right = root.right;
        root.right = root.left;
        root.left = null;
        return rightTail;
    }
}

class Solution2 {
    public void flatten(TreeNode root) {
        if (root == null)
            return;

        if (root.left != null) {
            TreeNode tail = root.left;
            while (tail.right != null)
                tail = tail.right;

            tail.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        flatten(root.right);
    }
}

class Solution3 {
    private TreeNode prev;

    public void flatten(TreeNode root) {
        prev = null;
        postOrderTraversal(root);
    }

    private void postOrderTraversal(TreeNode root) {
        if (root == null)
            return;

        postOrderTraversal(root.right);
        postOrderTraversal(root.left);

        root.left = null;
        root.right = prev;
        prev = root;
    }
}

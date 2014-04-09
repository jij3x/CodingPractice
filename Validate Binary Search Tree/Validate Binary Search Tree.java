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
    private TreeNode prev;

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return doIsValidBST(root);
    }

    private boolean doIsValidBST(TreeNode root) {
        if (root == null)
            return true;

        if (!doIsValidBST(root.left))
            return false;

        if (prev != null && prev.val >= root.val)
            return false;
        prev = root;

        return doIsValidBST(root.right);
    }
}

public class Solution2 {
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        int[] boundary = new int[2];
        return doIsValidBST(root, boundary);
    }

    private boolean doIsValidBST(TreeNode root, int[] boundary) {
        if (root.left == null && root.right == null) {
            boundary[0] = root.val;
            boundary[1] = root.val;
            return true;
        }

        int smallest = root.val, largest = root.val;

        if (root.left != null) {
            if (!doIsValidBST(root.left, boundary) || boundary[1] >= root.val)
                return false;

            smallest = boundary[0];
        }

        if (root.right != null) {
            if (!doIsValidBST(root.right, boundary) || boundary[0] <= root.val)
                return false;

            largest = boundary[1];
        }

        boundary[0] = smallest;
        boundary[1] = largest;
        return true;
    }
}

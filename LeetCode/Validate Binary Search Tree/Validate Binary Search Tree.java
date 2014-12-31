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
        return dfs(root);
    }

    private boolean dfs(TreeNode root) {
        if (root == null)
            return true;

        if (!dfs(root.left) || (prev != null && prev.val >= root.val))
            return false;
        prev = root;

        return dfs(root.right);
    }
}

class Solution2 {
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        return validateBST(root, new int[2]);
    }

    private boolean validateBST(TreeNode root, int[] boundary) {
        int min = root.val, max = min;

        if (root.left != null) {
            if (!validateBST(root.left, boundary) || root.val <= boundary[1])
                return false;
            min = boundary[0];
        }

        if (root.right != null) {
            if (!validateBST(root.right, boundary) || root.val >= boundary[0])
                return false;
            max = boundary[1];
        }

        boundary[0] = min;
        boundary[1] = max;
        return true;
    }
}

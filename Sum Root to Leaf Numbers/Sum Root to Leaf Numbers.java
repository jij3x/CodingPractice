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
    private int sum;

    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;

        sum = 0;
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode root, int num) {
        if (root.left == null && root.right == null) {
            sum += num * 10 + root.val;
            return;
        }

        if (root.left != null)
            dfs(root.left, num * 10 + root.val);
        if (root.right != null)
            dfs(root.right, num * 10 + root.val);
    }
}

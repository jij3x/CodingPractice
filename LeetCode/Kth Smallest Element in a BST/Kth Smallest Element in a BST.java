public class Solution {
    private int r, n;

    public int kthSmallest(TreeNode root, int k) {
        n = 0;
        dfs(root, k);
        return r;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null || n > k)
            return;

        dfs(root.left, k);
        if (n++ < k)
            r = root.val;
        dfs(root.right, k);
    }
}
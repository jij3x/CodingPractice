public class Solution {
    private TreeNode largestBST;
    private int maxSize, subtreeSize;

    public TreeNode findLargestBST(TreeNode root) {
        largestBST = null;
        maxSize = 0;
        dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return largestBST;
    }

    private TreeNode dfs(TreeNode root, int min, int max) {
        if (root == null || root.val > max || root.val < min) {
            subtreeSize = 0;
            return null;
        }

        int leftSize = 0;
        TreeNode leftTree = null;
        if (root.val != Integer.MIN_VALUE) {
            leftTree = dfs(root.left, min, root.val - 1);
            leftSize = subtreeSize;
        }

        int rightSize = 0;
        TreeNode rightTree = null;
        if (root.val != Integer.MAX_VALUE) {
            rightTree = dfs(root.right, root.val + 1, max);
            rightSize = subtreeSize;
        }

        TreeNode ret = new TreeNode(root.val);
        ret.left = leftTree;
        ret.right = rightTree;
        subtreeSize = leftSize + rightSize + 1;

        if (subtreeSize > maxSize) {
            maxSize = subtreeSize;
            largestBST = ret;
        }

        return ret;
    }
}

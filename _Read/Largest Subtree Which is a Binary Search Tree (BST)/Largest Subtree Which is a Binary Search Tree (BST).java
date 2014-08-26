public class Solution {
    private int treeMax, treeMin, maxNodes;
    private TreeNode largestBSTSubtree;

    public TreeNode findLargestBSTSubtree(TreeNode root) {
        if (root == null)
            return null;

        largestBSTSubtree = null;
        findBSTSubtree(root);
        return largestBSTSubtree;
    }

    private int findBSTSubtree(TreeNode root) {
        treeMax = Integer.MIN_VALUE;
        treeMin = Integer.MAX_VALUE;

        int leftCnt = 0;
        int rightCnt = 0;
        if (root.left == null && root.right == null) {
            treeMax = root.val;
            treeMin = root.val;
            return 1;
        } else if (root.left == null) {
            rightCnt = findBSTSubtree(root.right);
            if (rightCnt == -1 || treeMin <= root.val)
                return -1;

            treeMin = root.val;
            if (rightCnt + 1 > maxNodes) {
                maxNodes = rightCnt + 1;
                largestBSTSubtree = root;
            }
        } else if (root.right == null) {
            leftCnt = findBSTSubtree(root.left);
            if (leftCnt == -1 || treeMax >= root.val)
                return -1;

            treeMax = root.val;
            if (leftCnt + 1 > maxNodes) {
                maxNodes = leftCnt + 1;
                largestBSTSubtree = root;
            }
        } else {
            leftCnt = findBSTSubtree(root.left);
            if (leftCnt == -1 || treeMax >= root.val)
                return -1;
            int leftMin = treeMin;

            treeMax = Integer.MIN_VALUE;
            treeMin = Integer.MAX_VALUE;
            rightCnt = findBSTSubtree(root.right);
            if (rightCnt == -1 || treeMin <= root.val)
                return -1;
            treeMin = leftMin;
        }
        return leftCnt + rightCnt + 1;
    }
}

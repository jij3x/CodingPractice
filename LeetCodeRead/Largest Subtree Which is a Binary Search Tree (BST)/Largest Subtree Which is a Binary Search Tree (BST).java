public class Solution {
    private int subtreeMax, subtreeMin, maxSize;
    private TreeNode largestSBST;

    public TreeNode findLargestSBST(TreeNode root) {
        maxSize = 0;
        largestSBST = null;
        findSBST(root);
        return largestSBST;
    }

    private int findSBST(TreeNode root) {
        if (root == null)
            return 0;

        int leftSize = findSBST(root.left);
        if (leftSize == -1 || (root.left != null && subtreeMax >= root.val))
            return -1;
        int leftMin = leftSize == 0 ? root.val : subtreeMin;

        int rightSize = findSBST(root.right);
        if (rightSize == -1 || (root.right != null && subtreeMin <= root.val))
            return -1;
        if (rightSize == 0)
            subtreeMax = root.val;
        subtreeMin = leftMin;

        int size = leftSize + 1 + rightSize;
        if (size > maxSize) {
            maxSize = size;
            largestSBST = root;
        }

        return size;
    }
}

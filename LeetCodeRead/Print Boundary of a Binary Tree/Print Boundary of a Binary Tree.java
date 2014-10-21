public class Solution {
    private void printLeftEdges(TreeNode root, boolean leftmost) {
        if (root == null)
            return;
        if (leftmost || (root.left == null && root.right == null))
            System.out.print(", " + root.val);

        printLeftEdges(root.left, leftmost);
        printLeftEdges(root.right, false);
    }

    private void printRightEdges(TreeNode root, boolean rightmost) {
        if (root == null)
            return;

        printRightEdges(root.left, false);
        printRightEdges(root.right, rightmost);

        if (rightmost || (root.left == null && root.right == null))
            System.out.print(", " + root.val);
    }

    public void printOuterEdges(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val);

        printLeftEdges(root.left, true);
        printRightEdges(root.right, true);

        System.out.println();
    }
}
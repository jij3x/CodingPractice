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
    private TreeNode prev, errNode1, errNode2;

    public void recoverTree(TreeNode root) {
        prev = errNode1 = errNode2 = null;
        inOrderTraversal(root);
        int temp = errNode1.val;
        errNode1.val = errNode2.val;
        errNode2.val = temp;
    }

    private void inOrderTraversal(TreeNode root) {
        if (root == null)
            return;

        inOrderTraversal(root.left);

        if (prev != null && prev.val >= root.val) {
            errNode1 = errNode1 == null ? prev : errNode1;
            errNode2 = root;
        }
        prev = root;

        inOrderTraversal(root.right);
    }
}

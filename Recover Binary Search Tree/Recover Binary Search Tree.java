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
    private TreeNode prev, node1, node2;

    public void recoverTree(TreeNode root) {
        prev = node1 = node2 = null;
        inOrderTraversal(root);
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    private void inOrderTraversal(TreeNode root) {
        if (root == null)
            return;

        inOrderTraversal(root.left);

        if (prev != null && root.val < prev.val) {
            node1 = node1 == null ? prev : node1;
            node2 = root;
        }
        prev = root;

        inOrderTraversal(root.right);
    }
}

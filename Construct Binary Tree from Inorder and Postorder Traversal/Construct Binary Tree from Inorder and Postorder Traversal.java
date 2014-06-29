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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return dfs(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode dfs(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        if (is > ie)
            return null;

        TreeNode root = new TreeNode(postorder[pe]);
        int leftLen = 0;
        for (int i = is; inorder[i] != postorder[pe]; i++)
            leftLen++;

        root.left = dfs(inorder, is, is + leftLen - 1, postorder, ps, ps + leftLen - 1);
        root.right = dfs(inorder, is + leftLen + 1, ie, postorder, ps + leftLen, pe - 1);
        return root;
    }
}

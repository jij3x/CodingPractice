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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (ps > pe)
            return null;

        TreeNode root = new TreeNode(preorder[ps]);
        int leftLen = 0;
        for (int i = is; inorder[i] != preorder[ps]; i++)
            leftLen++;

        root.left = dfs(preorder, ps + 1, ps + leftLen, inorder, is, is + leftLen - 1);
        root.right = dfs(preorder, ps + leftLen + 1, pe, inorder, is + leftLen + 1, ie);
        return root;
    }
}

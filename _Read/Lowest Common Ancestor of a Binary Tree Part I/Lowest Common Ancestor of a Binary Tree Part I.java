public class Solution {
    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (root.val == p.val || root.val == q.val)
            return root;

        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }

    public TreeNodeWithParent LCA(TreeNodeWithParent p, TreeNodeWithParent q) {
        int pHeight = 0, qHeight = 0;
        for (TreeNodeWithParent curr = p; curr != null; curr = curr.parent)
            pHeight++;
        for (TreeNodeWithParent curr = q; curr != null; curr = curr.parent)
            qHeight++;

        if (pHeight > qHeight) {
            for (int i = 0; i < pHeight - qHeight; i++)
                p = p.parent;
        } else {
            for (int i = 0; i < qHeight - pHeight; i++)
                q = q.parent;
        }

        while (p != null) {
            if (p == q)
                return p;
            p = p.parent;
            q = q.parent;
        }
        return null;
    }
}

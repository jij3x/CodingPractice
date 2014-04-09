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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (p != null && q != null && p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

        return false;
    }
}

// Solution for case: if node can be pointed by more than one parent.
class Solution2 {
    public boolean isSameTree(TreeNode root1, TreeNode root2) {
        Map<TreeNode, TreeNode> visited = new HashMap<TreeNode, TreeNode>();

        return doIsSameTree(root1, root2, visited);
    }

    private boolean doIsSameTree(TreeNode root1, TreeNode root2, Map<TreeNode, TreeNode> visited) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null || root1.val != root2.val
                || visited.containsKey(root1) != visited.containsKey(root2))
            return false;

        if (visited.containsKey(root1))
            return visited.get(root1) == root2;

        visited.put(root1, root2);
        visited.put(root2, root1);

        return doIsSameTree(root1.left, root2.left, visited) && doIsSameTree(root1.right, root2.right, visited);
    }
}

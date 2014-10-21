public class Solution {
    public boolean sameBinaryTree(TreeNode node1, TreeNode node2) {
        HashSet<TreeNode> visited = new HashSet<TreeNode>();
        return isSameTree(node1, node2, visited);
    }

    private boolean isSameTree(TreeNode node1, TreeNode node2, HashSet<TreeNode> visited) {
        if (node1 == null && node2 == null)
            return true;
        else if (node1 == null || node2 == null)
            return false;

        if (node1.val == node2.val && visited.contains(node1) == visited.contains(node2)) {
            visited.add(node1);
            visited.add(node2);
            return isSameTree(node1.left, node2.left, visited) && isSameTree(node1.right, node2.right, visited);
        }
        return false;
    }
}
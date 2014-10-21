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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;

        dfs(root, 0, sum, new LinkedList<Integer>(), result);
        return result;
    }

    private void dfs(TreeNode root, int sum, int target, LinkedList<Integer> path, ArrayList<List<Integer>> result) {
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum + root.val == target)
                result.add(new ArrayList<Integer>(path));
        } else {
            if (root.left != null)
                dfs(root.left, sum + root.val, target, path, result);
            if (root.right != null)
                dfs(root.right, sum + root.val, target, path, result);
        }
        path.removeLast();
    }
}

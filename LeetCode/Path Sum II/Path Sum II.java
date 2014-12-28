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

        dfs(root, sum, new LinkedList<Integer>(), result);
        return result;
    }

    private void dfs(TreeNode root, int sum, LinkedList<Integer> path, ArrayList<List<Integer>> result) {
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (root.val == sum)
                result.add(new ArrayList<Integer>(path));
        } else {
            if (root.left != null)
                dfs(root.left, sum - root.val, path, result);
            if (root.right != null)
                dfs(root.right, sum - root.val, path, result);
        }
        path.removeLast();
    }
}

class Solution2 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null)
            return new ArrayList<List<Integer>>();

        return dfs(root, sum);
    }

    private List<List<Integer>> dfs(TreeNode root, int sum) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root.left == null && root.right == null) {
            if (root.val == sum)
                result.add(new LinkedList<Integer>());
        } else {
            if (root.left != null)
                result.addAll(dfs(root.left, sum - root.val));
            if (root.right != null)
                result.addAll(dfs(root.right, sum - root.val));
        }

        for (List<Integer> row : result) {
            row.add(0, root.val);
        }
        return result;
    }
}

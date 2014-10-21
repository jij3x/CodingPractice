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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        if (root == null)
            return result;

        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.add(root);
        List<Integer> row = new ArrayList<Integer>();
        for (int currLvl = 1, nextLvl = 0; currLvl > 0;) {
            TreeNode curr = que.poll();
            currLvl--;
            row.add(curr.val);

            if (curr.left != null) {
                que.add(curr.left);
                nextLvl++;
            }
            if (curr.right != null) {
                que.add(curr.right);
                nextLvl++;
            }

            if (currLvl == 0) {
                result.addFirst(row);
                row = new ArrayList<Integer>();
                currLvl = nextLvl;
                nextLvl = 0;
            }
        }
        return result;
    }
}

class Solution2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(root, 0, result);
        Collections.reverse(result);
        return result;
    }

    private void dfs(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null)
            return;

        if (result.size() <= level)
            result.add(new ArrayList<Integer>());

        result.get(level).add(root.val);
        dfs(root.left, level + 1, result);
        dfs(root.right, level + 1, result);
    }
}

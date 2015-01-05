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
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null)
            return result;

        result.add(new ArrayList<Integer>());
        Queue<TreeNode> q1 = new LinkedList<TreeNode>(), q2 = new LinkedList<TreeNode>();
        Queue<TreeNode> currLevel = q1, nextLevel = q2;
        currLevel.add(root);
        while (!currLevel.isEmpty()) {
            TreeNode node = currLevel.poll();
            result.get(result.size() - 1).add(node.val);

            if (node.left != null)
                nextLevel.add(node.left);
            if (node.right != null)
                nextLevel.add(node.right);

            if (currLevel.isEmpty() && !nextLevel.isEmpty()) {
                result.add(new ArrayList<Integer>());
                currLevel = currLevel == q1 ? q2 : q1;
                nextLevel = nextLevel == q1 ? q2 : q1;
            }
        }

        return result;
    }
}

class Solution2 {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null)
            return result;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int currLevelCnt = 1, nextLevelCnt = 0;

        result.add(new ArrayList<Integer>());
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            currLevelCnt--;
            result.get(result.size() - 1).add(node.val);

            if (node.left != null) {
                q.add(node.left);
                nextLevelCnt++;
            }
            if (node.right != null) {
                q.add(node.right);
                nextLevelCnt++;
            }

            if (currLevelCnt == 0 && nextLevelCnt > 0) {
                currLevelCnt = nextLevelCnt;
                nextLevelCnt = 0;
                result.add(new ArrayList<Integer>());
            }
        }

        return result;
    }
}

class Solution3 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null)
            return;

        if (result.size() == level)
            result.add(new ArrayList<Integer>());

        result.get(level).add(root.val);
        dfs(root.left, level + 1, result);
        dfs(root.right, level + 1, result);
    }
}

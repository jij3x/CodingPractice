/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return doGenerate(1, n);
    }

    private List<TreeNode> doGenerate(int start, int end) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSet = doGenerate(start, i - 1);
            List<TreeNode> rightSet = doGenerate(i + 1, end);

            for (TreeNode leftNode : leftSet) {
                for (TreeNode rightNode : rightSet) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode; // Need deep copy?
                    root.right = rightNode; // Need deep copy?
                    result.add(root);
                }
            }
        }
        return result;
    }
}

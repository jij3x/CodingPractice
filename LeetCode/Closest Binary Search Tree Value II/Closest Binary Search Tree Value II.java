/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        int closestIdx = 0;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Stack<TreeNode> stk = new Stack<TreeNode>();
        while (!stk.isEmpty() || root != null) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            arr.add(root.val);
            if (Math.abs(arr.get(arr.size() - 1) - target) < Math.abs(arr.get(closestIdx) - target))
                closestIdx = arr.size() - 1;
            root = root.right;
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = closestIdx, j = i + 1; k > 0; k--) {
            if (i >= 0 && (j == arr.size() || Math.abs(arr.get(i) - target) < Math.abs(arr.get(j) - target)))
                result.add(arr.get(i--));
            else
                result.add(arr.get(j++));
        }
        return result;
    }
}

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;

        Stack<TreeNode> stk1 = new Stack<TreeNode>(), stk2 = new Stack<TreeNode>();
        Stack<TreeNode> currStk = stk1, nextStk = stk2;
        currStk.push(root);
        ArrayList<Integer> row = new ArrayList<Integer>();
        while (!currStk.isEmpty()) {
            TreeNode curr = currStk.pop();
            row.add(curr.val);

            if (currStk == stk2) {
                if (curr.right != null)
                    nextStk.push(curr.right);
                if (curr.left != null)
                    nextStk.push(curr.left);
            } else {
                if (curr.left != null)
                    nextStk.push(curr.left);
                if (curr.right != null)
                    nextStk.push(curr.right);
            }

            if (currStk.isEmpty()) {
                currStk = currStk == stk1 ? stk2 : stk1;
                nextStk = nextStk == stk1 ? stk2 : stk1;
                result.add(row);
                row = new ArrayList<Integer>();
            }
        }
        return result;
    }
}

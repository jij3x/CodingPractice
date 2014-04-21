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
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null)
            return result;

        Stack<TreeNode> stack1 = new Stack<TreeNode>(), stack2 = new Stack<TreeNode>();
        Stack<TreeNode> curr = stack1, next = stack2;
        curr.push(root);
        ArrayList<Integer> row = new ArrayList<Integer>();
        while (!curr.isEmpty()) {
            TreeNode n = curr.pop();
            row.add(n.val);
            
            if (curr == stack1) {
                if (n.left != null)
                    next.push(n.left);
                if (n.right != null)
                    next.push(n.right);
            } else {
                if (n.right != null)
                    next.push(n.right);
                if (n.left != null)
                    next.push(n.left);
            }

            if (curr.isEmpty()) {
                curr = curr == stack1 ? stack2 : stack1;
                next = next == stack1 ? stack2 : stack1;
                result.add(row);
                row = new ArrayList<Integer>();
            }
        }
        return result;
    }
}

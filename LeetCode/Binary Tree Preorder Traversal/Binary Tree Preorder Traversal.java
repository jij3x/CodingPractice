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
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode curr = stack.pop();
            if (curr != null) {
                result.add(curr.val);
                stack.push(curr.right);
                stack.push(curr.left);
            }
        }
        return result;
    }
}

class Solution2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        doPreorderTraversal(root, result);
        return result;
    }

    private void doPreorderTraversal(TreeNode root, ArrayList<Integer> result) {
        if (root == null)
            return;

        result.add(root.val);
        doPreorderTraversal(root.left, result);
        doPreorderTraversal(root.right, result);
    }
}

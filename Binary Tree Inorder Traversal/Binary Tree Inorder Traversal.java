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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                result.add(curr.val);
                curr = curr.right;
            }
        }
        return result;
    }
}

class Solution2 {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        doInorderTraversal(root, result);

        return result;
    }

    private void doInorderTraversal(TreeNode root, ArrayList<Integer> result) {
        if (root == null)
            return;

        doInorderTraversal(root.left, result);
        result.add(root.val);
        doInorderTraversal(root.right, result);
    }
}

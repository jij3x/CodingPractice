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
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        TreeNode node = root, lastVisited = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.peek().right != null && stack.peek().right != lastVisited) {
                    node = stack.peek().right;
                } else {
                    lastVisited = stack.pop();
                    result.add(lastVisited.val);
                }
            }
        }
        return result;
    }
}

class Solution2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        doPostorderTraversal(root, result);

        return result;
    }

    private void doPostorderTraversal(TreeNode root, ArrayList<Integer> result) {
        if (root == null)
            return;

        doPostorderTraversal(root.left, result);
        doPostorderTraversal(root.right, result);
        result.add(root.val);
    }
}

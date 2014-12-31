/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    private TreeNode prev, curr;
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        prev = null;
        curr = root;
        stack = new Stack<TreeNode>();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return curr != null || !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if (!hasNext())
            return Integer.MAX_VALUE;

        if (curr == null) {
            curr = stack.pop();
        } else {
            while (curr.left != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        int result = curr.val;
        curr = curr.right;
        return result;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
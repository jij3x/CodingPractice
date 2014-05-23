class ThreadedTreeNode {
    int val;
    ThreadedTreeNode left, right;
    boolean leftThread, rightThread;

    ThreadedTreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public void inThread(ThreadedTreeNode root) {
        if (root == null)
            return;

    }

    private ArrayList<Integer> inorderTraversal(ThreadedTreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;

        ThreadedTreeNode curr = root;
        while (curr.left != null)
            curr = curr.left;

        result.add(curr.val);
        while (curr.right != root && curr.right != null) {
            if (!curr.rightThread) {
                while (!curr.leftThread)
                    curr = curr.left;

            }
        }

        return result;
    }
}
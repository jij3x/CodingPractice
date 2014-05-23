class ThreadedTreeNode {
    int val;
    ThreadedTreeNode left, right;
    boolean leftThread, rightThread;

    ThreadedTreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    private ThreadedTreeNode prev;

    public void inThreading(ThreadedTreeNode root) {
        prev = null;
        doInThreading(root);
    }

    private void doInThreading(ThreadedTreeNode root) {
        if (root == null)
            return;

        doInThreading(root.left);

        if (prev != null) {
            if (root.left == null) {
                root.left = prev;
                root.leftThread = true;
            }
            if (prev.right == null) {
                prev.right = root;
                prev.rightThread = true;
            }
        }
        prev = root;

        doInThreading(root.right);
    }

    public ArrayList<Integer> inorderTraversal(ThreadedTreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;

        ThreadedTreeNode curr = root;
        while (curr.left != null)
            curr = curr.left;

        result.add(curr.val);
        while (curr.right != null) {
            if (!curr.rightThread) {
                curr = curr.right;
                while (!curr.leftThread)
                    curr = curr.left;
            } else {
                curr = curr.right;
            }

            result.add(curr.val);
        }

        return result;
    }
}

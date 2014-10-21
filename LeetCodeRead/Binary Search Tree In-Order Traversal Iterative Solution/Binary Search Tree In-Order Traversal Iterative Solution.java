class ThreadedTreeNode {
    int val;
    ThreadedTreeNode left, right;
    boolean leftThread, rightThread;

    ThreadedTreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public void test() {
        ThreadedTreeNode n1 = new ThreadedTreeNode(1);
        ThreadedTreeNode n2 = new ThreadedTreeNode(2);
        ThreadedTreeNode n3 = new ThreadedTreeNode(3);
        ThreadedTreeNode n4 = new ThreadedTreeNode(4);
        ThreadedTreeNode n5 = new ThreadedTreeNode(5);
        ThreadedTreeNode n6 = new ThreadedTreeNode(6);
        ThreadedTreeNode n7 = new ThreadedTreeNode(7);

        n4.left = n2;
        n4.right = n6;
        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;

        inThreading(n4);
        System.out.println(inorderTraversal(n4));
    }

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

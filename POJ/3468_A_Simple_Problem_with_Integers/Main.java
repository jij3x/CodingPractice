/*
 * Segment Tree
 */

import java.io.BufferedInputStream;
import java.util.Scanner;

class SegmentTreeNode {
    public int leftBoundary, rightBoundary;
    public SegmentTreeNode left, right;
    public long sum, addDown;

    public SegmentTreeNode(int leftVal, int rightVal) {
        this.leftBoundary = leftVal;
        this.rightBoundary = rightVal;
    }
}

class Segment {
    private SegmentTreeNode root;
    private long sum;

    private SegmentTreeNode build(long[] A, int left, int right) {
        SegmentTreeNode node = new SegmentTreeNode(left, right);
        if (left == right) {
            node.sum = A[left];
            sum = A[left];
        } else {
            node.left = build(A, left, (left + right) / 2);
            long leftSum = sum;
            node.right = build(A, (left + right) / 2 + 1, right);
            sum += leftSum;
            node.sum = sum;
        }
        return node;
    }

    public Segment(long[] A) {
        this.root = build(A, 0, A.length - 1);
    }

    public void pushDown(SegmentTreeNode root) {
        if (root.addDown != 0) {
            root.left.addDown += root.addDown;
            root.left.sum += root.addDown * (root.left.rightBoundary - root.left.leftBoundary + 1);
            root.right.addDown += root.addDown;
            root.right.sum += root.addDown * (root.right.rightBoundary - root.right.leftBoundary + 1);
            root.addDown = 0;
        }
    }

    public void add(int left, int right, int val) {
        add(root, left - 1, right - 1, val);
    }

    private void add(SegmentTreeNode root, int left, int right, int val) {
        if (root.leftBoundary >= left && root.rightBoundary <= right) {
            root.sum += val * (root.rightBoundary - root.leftBoundary + 1);
            root.addDown += val;
            return;
        }

        pushDown(root);
        root.sum += val * (Math.min(root.rightBoundary, right) - Math.max(root.leftBoundary, left) + 1);

        if ((root.leftBoundary + root.rightBoundary) / 2 >= left && root.leftBoundary <= right)
            add(root.left, left, right, val);
        if (root.rightBoundary >= left && (root.leftBoundary + root.rightBoundary) / 2 + 1 <= right)
            add(root.right, left, right, val);
    }

    public long query(int left, int right) {
        return query(root, left - 1, right - 1);
    }

    private long query(SegmentTreeNode root, int left, int right) {
        if (root.leftBoundary >= left && root.rightBoundary <= right)
            return root.sum;

        pushDown(root);

        long result = 0;
        if ((root.leftBoundary + root.rightBoundary) / 2 >= left && root.leftBoundary <= right)
            result += query(root.left, left, right);
        if (root.rightBoundary >= left && (root.leftBoundary + root.rightBoundary) / 2 + 1 <= right)
            result += query(root.right, left, right);
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()) {
            int a = in.nextInt();
            int q = in.nextInt();
            long[] A = new long[a];
            for (int i = 0; i < a; i++)
                A[i] = in.nextLong();

            Segment segment = new Segment(A);

            while (q-- > 0) {
                String cmd = in.next();
                if (cmd.equals("Q"))
                    System.out.println(segment.query(in.nextInt(), in.nextInt()));
                else if (cmd.equals("C"))
                    segment.add(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }
        in.close();
    }
}
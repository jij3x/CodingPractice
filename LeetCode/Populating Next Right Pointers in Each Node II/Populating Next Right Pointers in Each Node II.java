/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null || (root.left == null && root.right == null))
            return;

        TreeLinkNode curr = root.next;
        while (curr != null && curr.left == null && curr.right == null)
            curr = curr.next;
        TreeLinkNode close = curr == null ? null : (curr.left == null ? curr.right : curr.left);
        TreeLinkNode open = root.right == null ? root.left : root.right;
        open.next = close;

        if (root.left != null && root.right != null)
            root.left.next = root.right;

        connect(root.right);
        connect(root.left);
    }
}

class Solution2 {
    public void connect(TreeLinkNode root) {
        for (TreeLinkNode curr = root, nextLvlHead = new TreeLinkNode(0), nextLvlTail = nextLvlHead; curr != null;) {
            if (curr.left != null) {
                nextLvlTail.next = curr.left;
                nextLvlTail = nextLvlTail.next;
            }
            if (curr.right != null) {
                nextLvlTail.next = curr.right;
                nextLvlTail = nextLvlTail.next;
            }

            curr = curr.next;
            if (curr == null) {
                curr = nextLvlHead.next;
                nextLvlHead = nextLvlTail = new TreeLinkNode(0);
            }
        }
    }
}

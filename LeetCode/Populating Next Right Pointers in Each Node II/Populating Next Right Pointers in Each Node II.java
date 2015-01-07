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
        if (root == null)
            return;

        TreeLinkNode currentLevelPivot = root;
        TreeLinkNode nextLevelHead = null;
        TreeLinkNode nextLevelTail = null;
        while (currentLevelPivot != null) {
            if (nextLevelHead == null && (currentLevelPivot.left != null || currentLevelPivot.right != null))
                nextLevelHead = currentLevelPivot.left == null ? currentLevelPivot.right : currentLevelPivot.left;

            if (currentLevelPivot.left != null) {
                if (nextLevelTail != null)
                    nextLevelTail.next = currentLevelPivot.left;

                nextLevelTail = currentLevelPivot.left;
            }

            if (currentLevelPivot.right != null) {
                if (nextLevelTail != null)
                    nextLevelTail.next = currentLevelPivot.right;

                nextLevelTail = currentLevelPivot.right;
            }

            if (currentLevelPivot.next == null) {
                currentLevelPivot = nextLevelHead;
                nextLevelHead = null;
                nextLevelTail = null;
            } else {
                currentLevelPivot = currentLevelPivot.next;
            }
        }
    }
}

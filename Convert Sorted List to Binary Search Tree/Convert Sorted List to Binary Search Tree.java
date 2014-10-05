/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
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
    private ListNode curr;

    public TreeNode sortedListToBST(ListNode head) {
        int len = 0;
        for (ListNode p = head; p != null; p = p.next)
            len++;

        this.curr = head;
        return convert(head, len);
    }

    private TreeNode convert(ListNode head, int len) {
        if (len == 0)
            return null;

        TreeNode left = convert(head, len / 2);
        TreeNode root = new TreeNode(this.curr.val);
        root.left = left;
        this.curr = this.curr.next;
        root.right = convert(this.curr, len - len / 2 - 1);
        return root;
    }
}

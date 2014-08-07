public class Solution {
    private TreeNode head, tail;

    public TreeNode treeToDoublyList(TreeNode root) {
        tail = head = null;

        convert(root);
        tail.right = head;
        head.left = tail;

        return head;
    }

    private void convert(TreeNode root) {
        if (root == null)
            return;

        convert(root.left);

        root.left = tail;
        if (tail == null)
            head = root;
        else
            tail.right = root;

        convert(root.right);
    }
}
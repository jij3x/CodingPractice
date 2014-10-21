public class Solution {
    private List<Integer> list;
    private int idx;

    private void inorderRead(TreeNode root) {
        if (root == null)
            return;

        inorderRead(root.left);
        list.add(root.val);
        inorderRead(root.right);
    }

    private void inorderWrite(TreeNode root) {
        if (root == null)
            return;

        inorderWrite(root.left);
        root.val = list.get(idx++);
        inorderWrite(root.right);
    }

    public void convertBinaryTree(TreeNode root) {
        list = new ArrayList<Integer>();
        inorderRead(root);

        Collections.sort(list);

        idx = 0;
        inorderWrite(root);
    }
}
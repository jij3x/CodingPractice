public class Solution {
    public boolean isSameBST(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null)
            return true;
        else if (r1 == null || r2 == null)
            return false;

        Queue<TreeNode> q1 = new LinkedList<TreeNode>();
        q1.add(r1);
        Queue<TreeNode> q2 = new LinkedList<TreeNode>();
        q2.add(r2);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode n1 = q1.poll();
            TreeNode n2 = q2.poll();
            if (n1.val != n2.val || (n1.left == null && n2.left != null))
                return false;

            if (n1.left != null)
                q1.add(n1.left);
            if (n1.right != null)
                q1.add(n1.right);

            if (n2.left != null)
                q2.add(n2.left);
            if (n2.right != null)
                q2.add(n2.right);
        }

        return q1.isEmpty() && q2.isEmpty();
    }
}

public class Solution {
    public void insert(ListNode node, int x) {
        if (node == null) {
            node = new ListNode(x);
            node.next = node;
            return;
        }

        ListNode prev = node, curr = node.next;
        while (curr != node) {
            if (x >= prev.val && x <= curr.val)
                break;
            if (prev.val > curr.val && (x < curr.val || x > prev.val))
                break;
            prev = prev.next;
            curr = curr.next;
        }

        ListNode newNode = new ListNode(x);
        newNode.next = curr;
        prev.next = newNode;
    }
}

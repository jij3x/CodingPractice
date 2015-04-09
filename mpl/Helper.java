public class Helper {

    // "Detect Cycle" input processor
    public static ListNode cyclizeIntSLList(ListNode node, int x) {
        if (node == null || x == -1)
            return node;

        ListNode tail = node;
        while (tail.next != null) {
            tail = tail.next;
        }

        ListNode ptr = node;
        for (int idx = 0; idx != x; idx++) {
            ptr = ptr.next;
        }

        tail.next = ptr;
        return node;
    }

    // "Detect Cycle" output processor
    public static String findIntSLListNodeIndex(ListNode head, ListNode node) {
        if (node == null)
            return "no cycle";

        for (int idx = 0; head != null; head = head.next, idx++) {
            if (head == node)
                return String.format("tail connects to node index %d", idx);
        }
        return "output node is not in the linked list";
    }

}
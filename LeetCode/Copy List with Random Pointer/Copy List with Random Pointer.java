/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        for (RandomListNode ptr = head; ptr != null; ptr = ptr.next.next) {
            RandomListNode node = new RandomListNode(ptr.label);
            node.next = ptr.next;
            ptr.next = node;
        }

        for (RandomListNode ptr = head; ptr != null; ptr = ptr.next.next) {
            ptr.next.random = ptr.random == null ? null : ptr.random.next;
        }

        RandomListNode start = new RandomListNode(0);
        for (RandomListNode tail = start; head != null; head = head.next) {
            tail.next = head.next;
            tail = tail.next;
            head.next = head.next.next;
        }
        return start.next;
    }
}

class Solution2 {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;

        HashMap<RandomListNode, RandomListNode> copyMap = new HashMap<RandomListNode, RandomListNode>();
        dfsCopy(head, copyMap);
        return copyMap.get(head);
    }

    private void dfsCopy(RandomListNode head, HashMap<RandomListNode, RandomListNode> copyMap) {
        RandomListNode cloneHead = new RandomListNode(head.label);
        copyMap.put(head, cloneHead);

        if (head.next != null && !copyMap.containsKey(head.next))
            dfsCopy(head.next, copyMap);
        cloneHead.next = head.next == null ? null : copyMap.get(head.next);

        if (head.random != null && !copyMap.containsKey(head.random))
            dfsCopy(head.random, copyMap);
        cloneHead.random = head.random == null ? null : copyMap.get(head.random);
    }
}

class Solution3 {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;

        HashMap<RandomListNode, RandomListNode> visited = new HashMap<RandomListNode, RandomListNode>();
        Queue<RandomListNode> que = new LinkedList<RandomListNode>();
        visited.put(head, new RandomListNode(head.label));
        que.add(head);
        while (!que.isEmpty()) {
            RandomListNode curr = que.poll();

            if (curr.next != null && !visited.containsKey(curr.next)) {
                visited.put(curr.next, new RandomListNode(curr.next.label));
                que.add(curr.next);
            }
            visited.get(curr).next = visited.get(curr.next);

            if (curr.random != null && !visited.containsKey(curr.random)) {
                visited.put(curr.random, new RandomListNode(curr.random.label));
                que.add(curr.random);
            }
            visited.get(curr).random = visited.get(curr.random);
        }

        return visited.get(head);
    }
}

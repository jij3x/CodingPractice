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
		if (head == null)
			return null;

		RandomListNode pivot = head;
		while (pivot != null) {
			RandomListNode cloneNode = new RandomListNode(pivot.label);
			cloneNode.next = pivot.next;
			pivot.next = cloneNode;
			pivot = cloneNode.next;
		}

		pivot = head;
		while (pivot != null) {
			pivot.next.random = (pivot.random == null ? null : pivot.random.next);
			pivot = pivot.next.next;
		}

		RandomListNode newHead = head.next;
		pivot = head;
		while (pivot != null) {
			RandomListNode cloneNode = pivot.next;
			pivot.next = cloneNode.next;
			if (cloneNode.next != null)
				cloneNode.next = pivot.next.next;
			pivot = pivot.next;
		}

		return newHead;
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

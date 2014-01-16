public class Solution {
	public class LRUCache {
		private class DoubleLinkedNode {
			public int key, val;
			public DoubleLinkedNode prev, next;

			public DoubleLinkedNode(int key, int val) {
				this.key = key;
				this.val = val;
				prev = null;
				next = null;
			}
		}

		private int capacity, size;
		private DoubleLinkedNode head, tail;
		private HashMap<Integer, DoubleLinkedNode> repo;

		public LRUCache(int capacity) {
			this.capacity = capacity;
			this.size = 0;
			head = new DoubleLinkedNode(0, 0);
			tail = new DoubleLinkedNode(0, 0);
			repo = new HashMap<Integer, DoubleLinkedNode>();
			head.next = tail;
			tail.prev = head;
		}

		private void refreshNode(DoubleLinkedNode currentNode, DoubleLinkedNode head, DoubleLinkedNode tail) {
			currentNode.prev.next = currentNode.next;
			currentNode.next.prev = currentNode.prev;
			currentNode.next = head.next;
			head.next.prev = currentNode;
			head.next = currentNode;
			currentNode.prev = head;
		}

		public int get(int key) {
			DoubleLinkedNode currentNode = repo.get(key);
			if (currentNode == null)
				return -1;

			refreshNode(currentNode, head, tail);
			return currentNode.val;
		}

		public void set(int key, int value) {
			DoubleLinkedNode currentNode = repo.get(key);
			if (currentNode == null) {
				currentNode = new DoubleLinkedNode(key, value);
				currentNode.next = head.next;
				head.next.prev = currentNode;
				head.next = currentNode;
				currentNode.prev = head;
				repo.put(key, currentNode);

				if (size + 1 > capacity) {
					repo.remove(tail.prev.key);
					tail.prev.prev.next = tail;
					tail.prev = tail.prev.prev;
				} else {
					size++;
				}
			} else {
				currentNode.val = value;
				refreshNode(currentNode, head, tail);
			}
		}
	}
}
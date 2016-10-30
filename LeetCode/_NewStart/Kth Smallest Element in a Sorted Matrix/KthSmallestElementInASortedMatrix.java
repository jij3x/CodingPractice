public class Solution {
  class Node {
    int val, y, x;

    Node(int val, int y, int x) {
      this.val = val;
      this.y = y;
      this.x = x;
    }
  }

  class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
      return node1.val - node2.val;
    }
  }

  public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    PriorityQueue<Node> pq = new PriorityQueue<>(n, new NodeComparator());
    for (int i = 0; i < n; i++) {
      pq.offer(new Node(matrix[0][i], 0, i));
    }

    int result = 0;
    for (int i = 0; i < k; i++) {
      Node node = pq.poll();
      result = node.val;
      if (node.y < n - 1) {
        pq.offer(new Node(matrix[node.y + 1][node.x], node.y + 1, node.x));
      }
    }
    return result;
  }
}
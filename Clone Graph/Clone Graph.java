/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;

        Map<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        Queue<UndirectedGraphNode> que = new LinkedList<UndirectedGraphNode>();
        que.add(node);
        visited.put(node, new UndirectedGraphNode(node.label));
        while (!que.isEmpty()) {
            UndirectedGraphNode curr = que.poll();
            for (UndirectedGraphNode n : curr.neighbors) {
                if (!visited.containsKey(n)) {
                    que.add(n);
                    visited.put(n, new UndirectedGraphNode(n.label));
                }
                visited.get(curr).neighbors.add(visited.get(n));
            }
        }

        return visited.get(node);
    }
}

class Solution2 {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return null;

		HashMap<Integer, UndirectedGraphNode> visited = new HashMap<Integer, UndirectedGraphNode>();
		dfs(node, visited);
		return visited.get(node.label);
	}

	private void dfs(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> visited) {
		UndirectedGraphNode returnNode = new UndirectedGraphNode(node.label);
		visited.put(node.label, returnNode);

		for (UndirectedGraphNode sn : node.neighbors) {
			if (!visited.containsKey(sn.label)) {
				dfs(sn, visited);
			}
			returnNode.neighbors.add(visited.get(sn.label));
		}
	}
}

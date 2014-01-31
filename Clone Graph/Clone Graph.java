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

		HashMap<Integer, UndirectedGraphNode> targets = new HashMap<Integer, UndirectedGraphNode>();
		Queue<UndirectedGraphNode> workingQue = new LinkedList<UndirectedGraphNode>();

		workingQue.add(node);
		UndirectedGraphNode returnNode = new UndirectedGraphNode(node.label);
		targets.put(node.label, returnNode);
		while (!workingQue.isEmpty()) {
			UndirectedGraphNode s = workingQue.poll();
			UndirectedGraphNode t = targets.get(s.label);
			for (int i = 0; i < s.neighbors.size(); i++) {
				UndirectedGraphNode sn = s.neighbors.get(i);
				if (!targets.containsKey(sn.label)) {
					workingQue.add(sn);
					targets.put(sn.label, new UndirectedGraphNode(sn.label));
				}
				t.neighbors.add(targets.get(sn.label));
			}
		}

		return returnNode;
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
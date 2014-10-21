class GraphNode {
    int val;
    ArrayList<GraphNode> neighbors;

    GraphNode(int val) {
        this.val = val;
        neighbors = new ArrayList<GraphNode>();
    }
}

public class Solution {
    public boolean hasCyclic(GraphNode root) {
        if (root == null)
            return false;

        return hasCyclic(root, new HashSet<GraphNode>(), new HashSet<GraphNode>());
    }

    private boolean hasCyclic(GraphNode root, HashSet<GraphNode> visited, HashSet<GraphNode> visiting) {
        if (root.neighbors.size() == 0)
            return false;

        visiting.add(root);
        for (GraphNode n : root.neighbors) {
            if (visiting.contains(n) || (!visited.contains(n) && hasCyclic(n, visited, visiting)))
                return true;
        }

        visiting.remove(root);
        visited.add(root);
        return false;
    }
}
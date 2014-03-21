/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 
 * Definition for N-ary tree
 * public class NaryTreeNode {
 *     int val;
 *     ArrayList<NaryTreeNode> children;
 *     NaryTreeNode(int x) { val = x; children = new ArrayList<NaryTreeNode>(); }
 * }
 */
public class Solution {
	// serialize binary tree (DFS fashion)
	public String dfsBTSerialize(TreeNode root) {
		StringBuilder result = new StringBuilder();
		doDfsBTSerialize(root, result);
		result.delete(0, 1);

		return result.toString();
	}

	private void doDfsBTSerialize(TreeNode root, StringBuilder result) {
		result.append(",");
		if (root == null) {
			result.append("n");
			return;
		}

		result.append(Integer.toString(root.val));
		doDfsBTSerialize(root.left, result);
		doDfsBTSerialize(root.right, result);
	}

	// deserialize binary tree (DFS fashion)
	public TreeNode dfsBTDeserialize(String s) {
		StringBuilder buffer = new StringBuilder(s);
		return doDfsBTDeserialize(buffer);
	}

	private TreeNode doDfsBTDeserialize(StringBuilder buffer) {
		TreeNode root = null;
		if (buffer.length() == 0)
			return root;

		StringBuilder token = new StringBuilder();
		while (buffer.length() > 0 && buffer.charAt(0) != ',') {
			token.append(buffer.charAt(0));
			buffer.delete(0, 1);
		}
		if (buffer.length() > 0)
			buffer.delete(0, 1);

		if (!token.toString().equals("n")) {
			root = new TreeNode(Integer.parseInt(token.toString()));
			root.left = doDfsBTDeserialize(buffer);
			root.right = doDfsBTDeserialize(buffer);
		}

		return root;
	}

	// serialize binary tree (BFS fashion)
	public String bfsBTSerialize(TreeNode root) {
		StringBuilder result = new StringBuilder();
		Queue<TreeNode> workingQue = new LinkedList<TreeNode>();
		workingQue.add(root);
		while (!workingQue.isEmpty()) {
			TreeNode curr = workingQue.poll();
			if (curr != null) {
				result.append(",").append(Integer.toString(curr.val));
				workingQue.add(curr.left);
				workingQue.add(curr.right);
			} else {
				result.append(",n");
			}
		}

		result.delete(0, 1);
		return result.toString();
	}

	// deserialize binary tree (BFS fashion)
	public TreeNode bfsBTDeserialize(String s) {
		s += ",";
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		StringBuilder token = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ',') {
				token.append(s.charAt(i));
			} else {
				TreeNode node = token.charAt(0) == 'n' ? null : new TreeNode(Integer.parseInt(token.toString()));
				list.add(node);
				token = new StringBuilder();
			}
		}

		for (int i = 1, j = 0, c = 0; i < list.size(); i++, c = (c + 1) % 2) {
			if (c == 0) {
				list.get(j).left = list.get(i);
			} else {
				list.get(j++).right = list.get(i);
				while (j < list.size() && list.get(j) == null) {
					j++;
				}
			}
		}
		return list.get(0);
	}

	// serialize N-ary tree (grafting to binary tree internally)
	public String NTSerialize(NaryTreeNode root) {
		TreeNode lcrsTree = grafting(root);
		return bfsBTSerialize(lcrsTree);
	}

	// deserialize N-ary tree (grafting to binary tree internally)
	public NaryTreeNode NTdeserialize(String s) {
		TreeNode lcrsTree = bfsBTDeserialize(s);
		return unGrafting(lcrsTree);
	}

	private TreeNode grafting(NaryTreeNode root) {
		if (root == null)
			return null;

		ArrayList<TreeNode> childList = new ArrayList<TreeNode>();
		TreeNode binaryNode = new TreeNode(root.val);
		for (int i = 0; i < root.children.size(); i++) {
			childList.add(grafting(root.children.get(i)));
			if (i > 0) {
				childList.get(i - 1).right = childList.get(i);
			}
		}

		binaryNode.left = childList.isEmpty() ? null : childList.get(0);
		return binaryNode;
	}

	private NaryTreeNode unGrafting(TreeNode root) {
		if (root == null)
			return null;

		NaryTreeNode naryNode = new NaryTreeNode(root.val);
		ArrayList<NaryTreeNode> childList = new ArrayList<NaryTreeNode>();
		TreeNode child = root.left;
		while (child != null) {
			childList.add(unGrafting(child));
			child = child.right;
		}

		naryNode.children = childList;
		return naryNode;
	}
}

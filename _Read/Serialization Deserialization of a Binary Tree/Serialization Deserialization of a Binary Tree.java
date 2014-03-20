public class Solution {
	public String serialize(TreeNode root) {
		StringBuilder result = new StringBuilder();
		doSerialize(root, result);
		result.delete(0, 1);

		return result.toString();
	}

	private void doSerialize(TreeNode root, StringBuilder result) {
		result.append(",");
		if (root == null) {
			result.append("n");
			return;
		}

		result.append(Integer.toString(root.val));
		doSerialize(root.left, result);
		doSerialize(root.right, result);
	}

	public TreeNode deserialize(String s) {
		StringBuilder buffer = new StringBuilder(s);
		return doDeserialize(buffer);
	}

	private TreeNode doDeserialize(StringBuilder buffer) {
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
			root.left = doDeserialize(buffer);
			root.right = doDeserialize(buffer);
		}

		return root;
	}
}

class Solution2 {
	public String serialize(TreeNode root) {
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

	public TreeNode deserialize(String s) {
		s += ",";
		Queue<TreeNode> workingQue = new LinkedList<TreeNode>();
		TreeNode root = null;
		String token = "";
		boolean toLeft = true;
		for (int p = 0; p < s.length(); p++) {
			if (s.charAt(p) == ',') {
				TreeNode curr = token.equals("n") ? null : new TreeNode(Integer.parseInt(token));
				if (workingQue.isEmpty()) {
					root = curr;
				} else {
					while (workingQue.peek() == null)
						workingQue.poll();

					if (toLeft) {
						workingQue.peek().left = curr;
						toLeft = false;
					} else {
						workingQue.poll().right = curr;
						toLeft = true;
					}
				}
				workingQue.add(curr);
				token = "";
			} else {
				token += s.charAt(p);
			}
		}

		return root;
	}
}

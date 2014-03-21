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
		List<TreeNode> list = new ArrayList<TreeNode>();
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
}

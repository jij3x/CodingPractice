public class Solution {
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		boolean[] visited = new boolean[num.length];
		ArrayList<Integer> path = new ArrayList<Integer>();
		subPermute(num, visited, path, result);
		return result;
	}

	private void subPermute(int[] num, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
		if (path.size() == num.length) {
			result.add(new ArrayList<Integer>(path));
		} else {
			for (int i = 0; i < num.length; i++) {
				if (visited[i] == false) {
					path.add(num[i]);
					visited[i] = true;
					subPermute(num, visited, path, result);
					visited[i] = false;
					path.remove(path.size() - 1);
				}
			}
		}
	}
}
public class Solution {
	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		Arrays.sort(num);
		boolean[] visited = new boolean[num.length];
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();

		dfs(num, path, result, visited);
		return result;
	}

	private void dfs(int[] num, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result, boolean[] visited) {
		if (path.size() == num.length) {
			ArrayList<Integer> subResult = new ArrayList<Integer>(path);
			result.add(subResult);
		}

		for (int i = 0; i < num.length; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			path.add(num[i]);

			dfs(num, path, result, visited);

			visited[i] = false;
			path.remove(path.size() - 1);

			while (i <= num.length - 2 && num[i] == num[i + 1]) {
				i++;
			}
		}
	}
}
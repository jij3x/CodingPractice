public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        doCombine(n, k, new LinkedList<Integer>(), result);
        return result;
    }

    private void doCombine(int n, int k, List<Integer> path, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<Integer>(path));
        } else {
            for (int i = n; i >= k; i--) {
                path.add(0, i);
                doCombine(i - 1, k - 1, path, result);
                path.remove(0);
            }
        }
    }
}

class Solution2 {
    public List<List<Integer>> combine(int n, int k) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (k == 0) {
            result.add(new ArrayList<Integer>());
        } else {
            for (int i = n; i >= k; i--) {
                for (List<Integer> r : combine(i - 1, k - 1)) {
                    r.add(i);
                    result.add(r);
                }
            }
        }
        return result;
    }
}

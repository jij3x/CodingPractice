public class Solution {
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();

        doCombine(n, k, path, result);
        return result;
    }

    private void doCombine(int n, int k, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        if (k == 0) {
            ArrayList<Integer> r = new ArrayList<Integer>(path);
            Collections.sort(r);
            result.add(r);
            return;
        }

        for (int i = n; i >= k; i--) {
            path.add(i);
            doCombine(i - 1, k - 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}

class Solution2 {
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (k == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }

        for (int i = n; i >= k; i--) {
            for (ArrayList<Integer> r : combine(i - 1, k - 1)) {
                r.add(i);
                Collections.sort(r);
                result.add(r);
            }
        }

        return result;
    }
}

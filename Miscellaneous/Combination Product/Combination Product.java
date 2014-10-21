public class Solution {
    public List<List<Integer>> combinationProduct(int num) {
        List<List<Integer>> result = doCombProd(num, 2);
        for (List<Integer> row : result) {
            if (row.size() == 1)
                row.add(0, 1);
        }
        return result;
    }

    private List<List<Integer>> doCombProd(int num, int start) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = start; i <= num; i++) {
            if (i == num) {
                result.add(new ArrayList<Integer>(Arrays.asList(i)));
            } else if (i < num && num % i == 0) {
                for (List<Integer> row : doCombProd(num / i, i)) {
                    row.add(0, i);
                    result.add(row);
                }
            }
        }
        return result;
    }
}

class Solution2 {
    public List<List<Integer>> combinationProduct(int num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        doCombProd(num, 2, new ArrayList<Integer>(), result);
        return result;
    }

    private void doCombProd(int num, int start, List<Integer> path, List<List<Integer>> result) {
        for (int i = start; i <= num; i++) {
            if (i == num) {
                ArrayList<Integer> r = new ArrayList<Integer>();
                if (path.isEmpty())
                    r.add(1);
                else
                    r.addAll(path);
                r.add(i);
                result.add(r);
            } else if (i < num && num % i == 0) {
                path.add(i);
                doCombProd(num / i, i, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}

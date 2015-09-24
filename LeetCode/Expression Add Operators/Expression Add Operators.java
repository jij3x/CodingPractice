public class Solution {
    public List<String> addOperators(String num, int target) {
        ArrayList<String> result = new ArrayList<String>();
        dfs(num, 0, "", 0, 0, target, result);
        return result;
    }

    private void dfs(String num, int start, String path, long acc, long prod, int target, List<String> result) {
        if (start == num.length()) {
            if (acc == target)
                result.add(path);
            return;
        }

        for (int i = start; i < num.length() && Long.parseLong(num.substring(start, i + 1)) <= Integer.MAX_VALUE; i++) {
            int n = Integer.parseInt(num.substring(start, i + 1));
            if (start == 0) {
                dfs(num, i + 1, Integer.toString(n), n, n, target, result);
            } else {
                dfs(num, i + 1, path + "+" + Integer.toString(n), acc + n, n, target, result);
                dfs(num, i + 1, path + "-" + Integer.toString(n), acc - n, -n, target, result);
                dfs(num, i + 1, path + "*" + Integer.toString(n), acc + prod * (n - 1), prod * n, target, result);
            }
            if (num.charAt(start) == '0')
                break;
        }
    }
}
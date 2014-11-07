public class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        dfs(n, n, "", result);
        return result;
    }

    private void dfs(int left, int right, String past, ArrayList<String> result) {
        if (right == 0)
            result.add(past);

        if (left > 0)
            dfs(left - 1, right, past + "(", result);
        if (right > left)
            dfs(left, right - 1, past + ")", result);
    }
}

class Solution2 {
    public List<String> generateParenthesis(int n) {
        return dfs(n, n);
    }

    private List<String> dfs(int left, int right) {
        List<String> result = new LinkedList<String>();
        if (right == 0)
            result.add("");

        if (left > 0) {
            for (String s : dfs(left - 1, right))
                result.add("(" + s);
        }
        if (right > left) {
            for (String s : dfs(left, right - 1))
                result.add(")" + s);
        }
        return result;
    }
}

class Solution3 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<String>();
        if (n == 0) {
            result.add("");
            return result;
        }

        List<String> subResult = generateParenthesis(n - 1);
        for (String r : subResult) {
            result.add("()" + r);

            Stack<String> stack = new Stack<String>();
            for (int i = 0; i < r.length(); i++) {
                if (r.charAt(i) == ')') {
                    stack.pop();
                    if (stack.isEmpty())
                        result.add("(" + r.substring(0, i + 1) + ")" + r.substring(i + 1, r.length()));
                } else {
                    stack.push("(");
                }
            }
        }
        return result;
    }
}

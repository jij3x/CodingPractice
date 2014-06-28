public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<String>();
        dfs("", n, n, result);
        return result;
    }

    private void dfs(String past, int left, int right, List<String> result) {
        if (right == 0) {
            result.add(past);
            return;
        }

        if (left > 0)
            dfs(past + "(", left - 1, right, result);
        if (right > left)
            dfs(past + ")", left, right - 1, result);
    }
}

class Solution2 {
    public List<String> generateParenthesis(int n) {
        return generate(n, n);
    }

    private List<String> generate(int left, int right) {
        List<String> result = new LinkedList<String>();
        if (right == 0) {
            result.add("");
            return result;
        }

        if (left == right) {
            for (String s : generate(left - 1, right))
                result.add("(" + s);
            return result;
        }

        for (String s : generate(left, right - 1))
            result.add(")" + s);

        if (left > 0)
            for (String s : generate(left - 1, right))
                result.add("(" + s);

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

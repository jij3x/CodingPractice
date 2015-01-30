public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                stk.push(ch);
            else if (stk.isEmpty() || (ch == ')' && stk.pop() != '(') || (ch == ']' && stk.pop() != '[')
                    || (ch == '}' && stk.pop() != '{'))
                return false;
        }
        return stk.isEmpty();
    }
}

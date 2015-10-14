public class Solution {
    public int calculate(String s) {
        int result = 0, num = 0, sign = 1;
        Stack<Integer> stk = new Stack<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + (s.charAt(i) - '0');
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                result += sign * num;
                num = 0;
                sign = s.charAt(i) == '+' ? 1 : -1;
            } else if (s.charAt(i) == '(') {
                stk.push(result);
                stk.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                result += sign * num;
                result = stk.pop() * result + stk.pop();
                num = 0;
            }
        }
        return result + sign * num;
    }
}

class Solution1 {
    public int calculate(String s) {
        int result = 0;
        for (int i = 0, op = 1; i < s.length();) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                op = s.charAt(i++) == '+' ? 1 : -1;
            } else {
                int n = 0;
                if (s.charAt(i) == '(') {
                    for (int j = ++i, m = 1; m != 0; i++) {
                        if (s.charAt(i) == '(')
                            m++;
                        else if (s.charAt(i) == ')' && --m == 0)
                            n = calculate(s.substring(j, i));
                    }
                } else {
                    int j = i;
                    while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9')
                        i++;
                    n = Integer.parseInt(s.substring(j, i));
                }
                result += op * n;
            }
        }
        return result;
    }
}
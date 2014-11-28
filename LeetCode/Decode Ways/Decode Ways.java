public class Solution {
    public int numDecodings(String s) {
        if (s.isEmpty())
            return 0;

        int[] memo = new int[s.length() + 2];
        memo[s.length()] = 1;
        memo[s.length() - 1] = s.charAt(s.length() - 1) == '0' ? 0 : 1;

        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) != '0')
                memo[i] += memo[i + 1] + (Integer.parseInt(s.substring(i, i + 2)) < 27 ? memo[i + 2] : 0);
        }
        return memo[0];
    }
}

class Solution2 {
    public int numDecodings(String s) {
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        memo[s.length()] = 1;
        return s.isEmpty() ? 0 : dpNumDecodings(s, 0, memo);
    }

    private int dpNumDecodings(String s, int start, int[] memo) {
        if (memo[start] != -1)
            return memo[start];
        if (s.charAt(start) == '0')
            return 0;

        memo[start] = dpNumDecodings(s, start + 1, memo);
        if (start < s.length() - 1 && Integer.parseInt(s.substring(start, start + 2)) <= 26)
            memo[start] += dpNumDecodings(s, start + 2, memo);
        return memo[start];
    }
}

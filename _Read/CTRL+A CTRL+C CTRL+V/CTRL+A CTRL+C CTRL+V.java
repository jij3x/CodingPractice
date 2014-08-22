public class Solution {
    public int findMaxM(int n) {
        int[] memo = new int[n + 1];
        return dpFindMaxM(n, memo);
    }

    private int dpFindMaxM(int n, int[] memo) {
        if (n <= 3)
            return n;
        if (memo[n] != 0)
            return memo[n];

        int max = n;
        for (int i = 4; n - i > 1; i++) {
            max = Math.max(max, dpFindMaxM(n - i, memo) * (i - 2));
        }
        memo[n] = max;
        return memo[n];
    }

    public List<String> printCombo(int n) {
        List<String> combo = new ArrayList<String>();
        if (n <= 3) {
            for (int i = n; i > 0; i--)
                combo.add("A");
            return combo;
        }

        int max = n, times = 0;
        for (int i = 4; n - i > 1; i++) {
            List<String> subCombo = printCombo(n - i);
            if (subCombo.size() * (i - 2) > max) {
                max = subCombo.size() * (i - 2);
                combo = subCombo;
                times = i - 2;
            }
        }

        if (times > 0) {
            combo.add("CTRL+A");
            combo.add("CTRL+C");
            for (int i = times; i > 0; i--)
                combo.add("CTRL+V");
        } else {
            for (int i = n; i > 0; i--)
                combo.add("A");
        }
        return combo;
    }
}

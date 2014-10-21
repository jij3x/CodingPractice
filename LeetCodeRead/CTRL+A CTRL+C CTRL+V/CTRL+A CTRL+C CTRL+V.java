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

    public List<String> printCombo2(int n) {
        String[] memo = new String[n + 1];
        memo[1] = "1A";
        memo[2] = "2A";
        memo[3] = "3A";

        dpPrintCombo(n, memo);

        return convertCombo(memo[n]);
    }

    private void dpPrintCombo(int n, String[] memo) {
        if (n <= 3)
            return;

        for (int i = 4; i <= n; i++) {
            memo[i] = Integer.toString(i) + "A";

            for (int j = i - 4; j > 1; j--) {
                String subCombo = memo[j];
                if (comboLength(subCombo) * (i - j - 2) > comboLength(memo[i])) {
                    memo[i] = subCombo + Integer.toString(i - j - 2) + "D";
                }
            }
        }
    }

    private long comboLength(String combo) {
        long result = 0;
        boolean aNotion = true;
        for (int i = 0, d = 0; i < combo.length(); i++) {
            if (combo.charAt(i) == 'A') {
                aNotion = false;
            } else if (combo.charAt(i) == 'D') {
                result *= d;
                d = 0;
            } else if (aNotion) {
                result = result * 10 + (combo.charAt(i) - '0');
            } else {
                d = d * 10 + (combo.charAt(i) - '0');
            }
        }
        return result;
    }

    private List<String> convertCombo(String combo) {
        List<String> result = new ArrayList<String>();
        boolean aNotion = true;
        for (int i = 0, a = 0, d = 0; i < combo.length(); i++) {
            if (combo.charAt(i) == 'A') {
                for (int j = a; j > 0; j--)
                    result.add("A");
                aNotion = false;
            } else if (combo.charAt(i) == 'D') {
                result.add("CTRL+A");
                result.add("CTRL+C");
                for (int j = d; j > 0; j--)
                    result.add("CTRL+V");
                d = 0;
            } else if (aNotion) {
                a = a * 10 + (combo.charAt(i) - '0');
            } else {
                d = d * 10 + (combo.charAt(i) - '0');
            }
        }
        return result;
    }
}

public class Solution {
    public boolean isWinning(int max, int target) {
        byte[] memo = new byte[target + 1];
        return dfs(max, target, memo);
    }

    private boolean dfs(int max, int total, byte[] memo) {
        if (max >= total)
            return true;
        if (memo[total] != 0)
            return memo[total] == 1 ? true : false;

        for (int i = 1; i <= max; i++) {
            if (!dfs(max, total - i, memo)) {
                memo[total] = 1;
                return true;
            }
        }
        memo[total] = -1;
        return false;
    }
}

class Solution2 {
    public boolean isWinning(int max, int target) {
        if (max >= target)
            return true;

        for (int i = 1; i <= max; i++) {
            if (!isWinning(max, target - i))
                return true;
        }
        return false;
    }
}

class Solution3 {
    public boolean isWinning(int max, int target) {
        boolean[] memo = new boolean[target + 1];
        Arrays.fill(memo, 1, max + 1, true);
        for (int i = max + 1; i <= target; i++) {
            for (int j = 1; j <= max; j++) {
                if (!memo[i - j])
                    memo[i] = true;
            }
        }
        return memo[target];
    }
}

class Solution4 {
    public boolean isWinning(int max, int target) {
        return max >= target || target % (max + 1) == 0;
    }
}

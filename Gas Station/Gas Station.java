public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0, start = 0;
        for (int i = 0, gasLeft = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];

            gasLeft += gas[i] - cost[i];
            if (gasLeft < 0) {
                start = i + 1;
                gasLeft = 0;
            }
        }
        return totalGas >= totalCost ? start : -1;
    }
}

class Solution2 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0, prev = -1; prev < i;) {
            prev = i;
            for (int gasLeft = 0; gasLeft >= 0;) {
                gasLeft += gas[i] - cost[i];
                i = (i + 1) % gas.length;
                if (i == prev && gasLeft >= 0)
                    return i;
            }
        }
        return -1;
    }
}

class Solution3 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int end = 0, gasLeft = 0;
        for (int i = 0, min = 0; i < gas.length; i++) {
            gasLeft += gas[i] - cost[i];
            if (gasLeft < min) {
                min = gasLeft;
                end = i;
            }
        }
        return gasLeft >= 0 ? (end + 1) % gas.length : -1;
    }
}

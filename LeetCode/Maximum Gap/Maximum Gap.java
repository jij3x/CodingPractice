public class Solution {
    public int maximumGap(int[] num) {
        if (num == null || num.length < 2)
            return 0;

        int min = num[0], max = num[0];
        for (int i = 1; i < num.length; i++) {
            min = Math.min(min, num[i]);
            max = Math.max(max, num[i]);
        }

        int[] bkts = new int[num.length * 2];
        Arrays.fill(bkts, -1);
        for (int i = 0, bktSize = (int) Math.ceil((double) (max - min + 1) / num.length); i < num.length; i++) {
            int n = (num[i] - min) / bktSize * 2;
            bkts[n] = bkts[n] == -1 ? num[i] : Math.min(bkts[n], num[i]);
            bkts[n + 1] = bkts[n + 1] == -1 ? num[i] : Math.max(bkts[n + 1], num[i]);
        }

        int maxGap = 0;
        for (int i = 0, prev = min; i < bkts.length - 1; i++) {
            if (bkts[i] != -1) {
                maxGap = Math.max(maxGap, bkts[i] - prev);
                prev = bkts[i];
            }
        }
        return maxGap;
    }
}

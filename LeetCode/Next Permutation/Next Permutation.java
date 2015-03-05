public class Solution {
    public void nextPermutation(int[] num) {
        int maxIdx = num.length - 1;
        while (maxIdx > 0 && num[maxIdx] <= num[maxIdx - 1]) {
            maxIdx--;
        }

        if (maxIdx > 0) {
            int k = maxIdx, tgt = num[maxIdx - 1];
            while (k < num.length - 1 && !(num[k + 1] <= tgt)) {
                k++;
            }
            int temp = num[maxIdx - 1];
            num[maxIdx - 1] = num[k];
            num[k] = temp;
        }

        for (int i = maxIdx, j = num.length - 1; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }
}

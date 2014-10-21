public class Solution {
    public void nextPermutation(int[] num) {
        int maxIdx = num.length - 1;
        while (maxIdx > 0) {
            if (num[maxIdx] > num[maxIdx - 1])
                break;
            maxIdx--;
        }

        if (maxIdx != 0) {
            int i = maxIdx;
            while (i < num.length - 1) {
                if (num[i] > num[maxIdx - 1] && num[i + 1] <= num[maxIdx - 1])
                    break;
                i++;
            }

            int temp = num[i];
            num[i] = num[maxIdx - 1];
            num[maxIdx - 1] = temp;
        }

        for (int i = maxIdx, j = num.length - 1; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }
}

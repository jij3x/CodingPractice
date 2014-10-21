public class Solution {
    public int[] reservoirSampling(int stream[], int k) {
        Random rand = new Random();
        int[] reservoir = new int[k];
        int n = 1;
        for (int i : stream) {
            if (n <= k) {
                reservoir[n - 1] = i;
            } else {
                int r = rand.nextInt(n);
                if (r < k)
                    reservoir[r] = i;
            }
            n++;
        }
        return reservoir;
    }
}
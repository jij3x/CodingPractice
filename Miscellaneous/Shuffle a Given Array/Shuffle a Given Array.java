public class Solution {
    public void shuffle(int[] A) {
        Random rand = new Random();
        for (int i = A.length - 1; i > 0; i--) {
            int randNum = rand.nextInt(i + 1);
            swap(A, i, randNum);
        }
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

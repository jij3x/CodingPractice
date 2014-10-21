public class Solution {
    boolean existsInfluencer(int[][] A) {
        newIter: for (int i = 0; i < A.length; i++) {
            for (int x = 0; x < A.length; x++) {
                if (x != i && A[i][x] == 0)
                    continue newIter;
            }
            for (int y = 0; y < A.length; y++) {
                if (A[y][i] == 1)
                    continue newIter;
            }
            return true;
        }
        return false;
    }

    boolean existsInfluencer2(int[][] A) {
        int[] inDegree = new int[A.length];
        int[] outDegree = new int[A.length];

        for (int y = 0; y < A.length; y++) {
            for (int x = 0; x < A.length; x++) {
                if (A[y][x] == 1) {
                    inDegree[x]++;
                    outDegree[y]++;
                }
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (inDegree[i] == 0 && outDegree[i] == A.length - 1)
                return true;
        }
        return false;
    }
}
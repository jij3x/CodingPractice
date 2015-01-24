public class Solution {
    public int removeElement(int[] A, int elem) {
        int len = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != elem)
                A[len++] = A[i];
        }
        return len;
    }
}

class Solution2 {
    public int removeElement(int[] A, int elem) {
        int len = A.length;
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == elem)
                A[i] = A[--len];
        }
        return len;
    }
}

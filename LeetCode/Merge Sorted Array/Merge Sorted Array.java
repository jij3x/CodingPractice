public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        for (int total = m + n; total > 0; total--) {
            if (m > 0 && n > 0)
                A[total - 1] = A[m - 1] > B[n - 1] ? A[--m] : B[--n];
            else if (m > 0)
                return;
            else
                A[total - 1] = B[--n];
        }
    }
}

class Solution2 {
    public void merge(int A[], int m, int B[], int n) {
        while (n > 0)
            A[m + n - 1] = (m <= 0 || A[m - 1] < B[n - 1]) ? B[--n] : A[--m];
    }
}

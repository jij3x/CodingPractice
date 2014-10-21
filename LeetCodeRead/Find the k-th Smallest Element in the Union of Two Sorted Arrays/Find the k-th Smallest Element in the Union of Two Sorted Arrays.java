/*
 * O(log(k))
 */
public class Solution {
    public int findKthSmallest(int[] A, int as, int al, int[] B, int bs, int bl, int k) {
        if (al == 0)
            return B[bs + k - 1];
        if (bl == 0)
            return A[as + k - 1];
        if (k == 1)
            return Math.min(A[as], B[bs]);

        int a = al < bl ? Math.min(al, k / 2) : k - Math.min(bl, k / 2);
        int b = k - a;

        if (A[as + a - 1] < B[bs + b - 1])
            return findKthSmallest(A, as + a, al - a, B, bs, bl, k - a);

        return findKthSmallest(A, as, al, B, bs + b, bl - b, k - b);
    }
}

/*
 * O(log(k))
 */
class Solution2 {
    public int findKthSmallest(int[] A, int as, int al, int[] B, int bs, int bl, int k) {
        if (al > bl)
            return findKthSmallest(B, bs, bl, A, as, al, k);
        if (al == 0)
            return B[bs + k - 1];
        if (k == 1)
            return Math.min(A[as], B[bs]);

        int m = Math.min(al, k / 2);
        int n = k - m;

        if (A[as + m - 1] <= B[bs + n - 1])
            return findKthSmallest(A, as + m, al - m, B, bs, n, k - m);

        return findKthSmallest(A, as, m, B, bs + n, bl - n, k - n);
    }
}

class Solution3 {
    public int findKthSmallest(int[] A, int as, int al, int[] B, int bs, int bl, int k) {
        if (al == 0)
            return B[bs + k - 1];
        if (bl == 0)
            return A[as + k - 1];
        if (k == 1)
            return Math.min(A[as], B[bs]);

        int m = Math.max(1, k * al / (al + bl));
        int n = k - m;
        if (A[as + m - 1] >= B[bs + n - 1] && (n == bl || A[as + m - 1] <= B[bs + n]))
            return A[as + m - 1];
        if (B[bs + n - 1] >= A[as + m - 1] && (m == al || B[bs + n - 1] <= A[as + m]))
            return B[bs + n - 1];

        if (A[as + m - 1] < B[bs + n - 1])
            return findKthSmallest(A, as + m, al - m, B, bs, n - 1, k - m);

        return findKthSmallest(A, as, m - 1, B, bs + n, bl - n, k - n);
    }
}

// O(log(k))
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

class Solution2 {
    public int findKthSmallest(int[] A, int as, int al, int[] B, int bs, int bl, int k) {
        if (al == 0)
            return B[bs + k - 1];
        if (bl == 0)
            return A[as + k - 1];
        if (k == 1)
            return Math.min(A[as], B[bs]);

        int a = Math.max(1, k * al / (al + bl));
        int b = k - a;
        if (A[as + a - 1] >= B[bs + b - 1] && (b == bl || A[as + a - 1] <= B[bs + b]))
            return A[as + a - 1];
        if (B[bs + b - 1] >= A[as + a - 1] && (a == al || B[bs + b - 1] <= A[as + a]))
            return B[bs + b - 1];

        if (A[as + a - 1] < B[bs + b - 1])
            return findKthSmallest(A, as + a, al - a, B, bs, b - 1, k - a);

        return findKthSmallest(A, as, a - 1, B, bs + b, bl - b, k - b);
    }
}

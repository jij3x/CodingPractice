public class Solution extends Reader4 {
    private char[] repo = new char[4];
    private int repoSize = 0;

    public int read(char[] buf, int n) {
        int total = repoSize;
        System.arraycopy(repo, 0, buf, 0, repoSize);

        int last = 4;
        while (total < n && last == 4) {
            last = read4(repo);
            System.arraycopy(repo, 0, buf, total, last);
            total += last;
        }

        repoSize = Math.max(0, total - n);
        if (total > n)
            System.arraycopy(buf, n, repo, 0, repoSize);
        return total - repoSize;
    }
}

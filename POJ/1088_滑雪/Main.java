/*
 * Dynamic Programming
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private static int m, n;
    private static int[][] height, memo;
    private static int max = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        m = in.nextInt();
        n = in.nextInt();
        height = new int[m][n];
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++)
                height[y][x] = in.nextInt();
        }
        in.close();

        memo = new int[m][n];
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++)
                max = Math.max(max, dp(x, y));
        }

        System.out.println(max);
    }

    private static int dp(int x, int y) {
        if (memo[y][x] != 0)
            return memo[y][x];

        int len = 0;

        if (x > 0 && height[y][x - 1] < height[y][x])
            len = Math.max(len, dp(x - 1, y));

        if (y > 0 && height[y - 1][x] < height[y][x])
            len = Math.max(len, dp(x, y - 1));

        if (x < n - 1 && height[y][x + 1] < height[y][x])
            len = Math.max(len, dp(x + 1, y));

        if (y < m - 1 && height[y + 1][x] < height[y][x])
            len = Math.max(len, dp(x, y + 1));

        memo[y][x] = len + 1;
        return memo[y][x];
    }
}

class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        final int[][] height = new int[m][n];
        Integer[] coords = new Integer[m * n];

        for (int y = 0, i = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                height[y][x] = in.nextInt();
                coords[i++] = (y << 8) | x;
            }
        }
        in.close();

        Arrays.sort(coords, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return height[b >> 8][b & 127] - height[a >> 8][a & 127];
            }
        });

        int max = 0;
        int[][] memo = new int[m][n];
        for (Integer s : coords) {
            int y = s >> 8;
            int x = s & 127;
            int len = 0;

            if (y > 0 && height[y - 1][x] > height[y][x])
                len = memo[y - 1][x];

            if (y < m - 1 && height[y + 1][x] > height[y][x])
                len = Math.max(len, memo[y + 1][x]);

            if (x > 0 && height[y][x - 1] > height[y][x])
                len = Math.max(len, memo[y][x - 1]);

            if (x < n - 1 && height[y][x + 1] > height[y][x])
                len = Math.max(len, memo[y][x + 1]);

            memo[y][x] = len + 1;
            max = Math.max(max, memo[y][x]);
        }

        System.out.println(max);
    }
}

class fastMain {
    private static byte[] buf = new byte[50 * 1024];
    private static int cur, bufLen;

    private final static int nextInt() {
        while (buf[cur] < '0' || buf[cur] > '9')
            cur++;

        int result = 0;
        while (cur < bufLen && buf[cur] >= '0' && buf[cur] <= '9')
            result = result * 10 + buf[cur++] - '0';
        return result;
    }

    private static int m, n;
    private static int[][] height, memo;

    public static void main(String[] args) throws IOException {
        bufLen = System.in.read(buf);
        m = nextInt();
        n = nextInt();
        height = new int[m][n];
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++)
                height[y][x] = nextInt();
        }

        memo = new int[m][n];
        int max = 0;
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                dp(x, y);
                if (memo[y][x] > max)
                    max = memo[y][x];
            }
        }

        System.out.println(max);
    }

    private static void dp(int x, int y) {
        if (memo[y][x] != 0)
            return;

        int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (yy >= 0 && yy < m && xx >= 0 && xx < n && height[yy][xx] < height[y][x]) {
                dp(x + dx[i], y + dy[i]);
                if (memo[yy][xx] > memo[y][x])
                    memo[y][x] = memo[yy][xx];
            }
        }

        memo[y][x] += 1;
    }
}

class fastMain2 {
    private static int MAXH = 10001;
    private static byte[] buf = new byte[50 * 1024];
    private static int cur, bufLen;

    private final static int nextInt() {
        while (buf[cur] < '0' || buf[cur] > '9')
            cur++;

        int result = 0;
        while (cur < bufLen && buf[cur] >= '0' && buf[cur] <= '9')
            result = result * 10 + buf[cur++] - '0';
        return result;
    }

    public static void main(String[] args) throws IOException {
        bufLen = System.in.read(buf);
        int m = nextInt();
        int n = nextInt();
        final int[][] height = new int[m][n];

        int visitedBit = 0x8000, tailBit = 0x4000, firstMask = 0x3FFF;
        int[] meta = new int[MAXH + 1];
        meta[MAXH] = tailBit;

        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                int h = nextInt();
                height[y][x] = h;

                int idx = y * n + x;
                if ((meta[h] & visitedBit) != 0) {
                    int first = meta[h] & firstMask;
                    meta[h] &= ~firstMask;
                    meta[h] |= idx;
                    meta[idx] |= (first << 16);
                } else {
                    meta[h] |= visitedBit | idx;
                    meta[idx] |= (MAXH << 16);
                }
            }
        }

        int max = 0;
        int[][] memo = new int[m][n];
        int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };
        for (int i = MAXH - 1; i >= 0; i--) {
            if ((meta[i] & visitedBit) == 0)
                continue;

            int next = meta[i] & firstMask;
            int tail = 0;
            while (tail == 0) {
                int y = next / n;
                int x = next % n;
                next = meta[next] >> 16;
                tail = meta[next] & tailBit;

                int len = 0;
                for (int j = 0; j < 4; j++) {
                    int yy = y + dy[j];
                    int xx = x + dx[j];
                    if (yy >= 0 && yy < m && xx >= 0 && xx < n && height[yy][xx] > height[y][x])
                        if (memo[yy][xx] > len)
                            len = memo[yy][xx];
                }

                memo[y][x] = len + 1;
                max = Math.max(max, memo[y][x]);
            }
        }

        System.out.println(max);
    }
}

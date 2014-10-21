import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private final static int MAXN = 1002;
    private static int[][] biTree = new int[MAXN][MAXN];
    private static int n;

    private static void update(int x, int y) {
        while (y <= n) {
            int x1 = x;
            while (x1 <= n) {
                biTree[y][x1]++;
                x1 += (x1 & -x1);
            }
            y += (y & -y);
        }
    }

    private static void update(int x1, int y1, int x2, int y2) {
        update(x2 + 1, y2 + 1);
        update(x2 + 1, y1);
        update(x1, y2 + 1);
        update(x1, y1);
    }

    private static void query(int x, int y) {
        int sum = 0;
        while (y > 0) {
            int x1 = x;
            while (x1 > 0) {
                sum += biTree[y][x1];
                x1 -= (x1 & -x1);
            }
            y -= (y & -y);
        }
        System.out.println(sum & 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int caseNum = in.nextInt(); caseNum > 0; caseNum--) {
            n = in.nextInt();
            for (int i = 0; i <= n; i++)
                Arrays.fill(biTree[i], 1, n + 1, 0);

            for (int i = in.nextInt(); i > 0; i--) {
                String cmd = in.next();
                if (cmd.equals("Q"))
                    query(in.nextInt(), in.nextInt());
                else
                    update(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
            }
            System.out.println();
        }
        in.close();
    }
}

class fastMain {
    private static int[][] biTree = new int[1002][32];
    private static int n;
    private static byte[] inBuf = new byte[2640 * 1024];
    private static byte[] outBuf = new byte[50 * 1024];
    private static int inCur, inLength, outCur;
    private static String newLine = "\n";

    private final static void update(short x, short y) {
        while (y <= n) {
            int x1 = x;
            while (x1 <= n) {
                biTree[y][x1 >> 5] ^= 1 << (x1 & 31);
                x1 += (x1 & -x1);
            }
            y += (y & -y);
        }
    }

    private final static void query(short x, short y) {
        int sum = 0;
        while (y > 0) {
            int x1 = x;
            while (x1 > 0) {
                sum ^= (biTree[y][x1 >> 5] >> (x1 & 31)) & 1;
                x1 -= (x1 & -x1);
            }
            y -= (y & -y);
        }
        outBuf[outCur++] = (byte) (sum + '0');
        outBuf[outCur++] = (byte) newLine.charAt(0);
    }

    private final static short nextShort() {
        while (inBuf[inCur] < '0' || inBuf[inCur] > '9')
            inCur++;

        short result = 0;
        while (inCur < inLength && inBuf[inCur] >= '0' && inBuf[inCur] <= '9')
            result = (short) (result * 10 + inBuf[inCur++] - '0');
        return result;
    }

    private final static int nextInt() {
        while (inBuf[inCur] < '0' || inBuf[inCur] > '9')
            inCur++;

        int result = 0;
        while (inCur < inLength && inBuf[inCur] >= '0' && inBuf[inCur] <= '9')
            result = result * 10 + inBuf[inCur++] - '0';
        return result;
    }

    private final static char nextCmd() {
        while (inBuf[inCur] != 'C' && inBuf[inCur] != 'Q')
            inCur++;

        return (char) inBuf[inCur++];
    }

    public static void main(String[] args) throws IOException {
        inLength = System.in.read(inBuf);

        for (int caseNum = nextInt(); caseNum > 0; caseNum--) {
            n = nextInt();
            for (int i = 0; i <= n; i++)
                for (int j = 0; j <= (n >> 5); j++)
                    biTree[i][j] = 0;

            for (int i = nextInt(); i > 0; i--) {
                if (nextCmd() == 'Q') {
                    query(nextShort(), nextShort());
                } else {
                    short x1 = nextShort(), y1 = nextShort(), x2 = nextShort(), y2 = nextShort();
                    update(x1, y1);
                    update(x1, (short) (y2 + 1));
                    update((short) (x2 + 1), y1);
                    update((short) (x2 + 1), (short) (y2 + 1));
                }
            }
            outBuf[outCur++] = (byte) newLine.charAt(0);
        }

        System.out.write(outBuf, 0, outCur);
    }
}

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final int MAXX = 32001;
    private static int[] biTree = new int[MAXX + 1];

    private static void update(int idx) {
        while (idx <= MAXX) {
            biTree[idx]++;
            idx += idx & (-idx);
        }
    }

    private static int read(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += biTree[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int stars = in.nextInt();
        int[] result = new int[stars];

        for (int i = 0; i < stars; i++) {
            int x = in.nextInt();
            in.nextInt();

            result[read(x + 1)]++;
            update(x + 1);
        }
        in.close();

        for (int i = 0; i < stars; i++)
            System.out.println(result[i]);
    }
}

class fastMain {
    private static byte[] inBuf = new byte[256 * 1024];
    private static int inCur, inLength;

    private final static short nextShort() {
        while (inBuf[inCur] < '0' || inBuf[inCur] > '9')
            inCur++;

        short result = 0;
        while (inCur < inLength && inBuf[inCur] >= '0' && inBuf[inCur] <= '9')
            result = (short) (result * 10 + inBuf[inCur++] - '0');
        return result;
    }

    private static final short MAXX = 32001;
    private static short[] biTree = new short[MAXX + 1];

    private static void update(int idx) {
        while (idx <= MAXX) {
            biTree[idx]++;
            idx += idx & (-idx);
        }
    }

    private static short read(int idx) {
        short sum = 0;
        while (idx > 0) {
            sum += biTree[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        inLength = System.in.read(inBuf);
        short stars = nextShort();
        short[] result = new short[stars];

        for (short i = 0; i < stars; i++) {
            int x = nextShort();
            nextShort();

            result[read(x + 1)]++;
            update(x + 1);
        }

        StringBuilder outBuf = new StringBuilder();
        for (short i = 0; i < stars; i++)
            outBuf.append(result[i]).append("\n");
        System.out.println(outBuf.toString());
    }
}

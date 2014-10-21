class Segment {
    private static final int N = 50001 * 3;
    private static int[] lBndry = new int[N], rBndry = new int[N], rngMax = new int[N], rngMin = new int[N];
    private int max, min;

    public void buildSegTree(int[] heights, int root, int lb, int rb) {
        lBndry[root] = lb;
        rBndry[root] = rb;

        if (lb == rb) {
            rngMax[root] = rngMin[root] = heights[lb];
            min = max = heights[lb];
            return;
        }

        buildSegTree(heights, root * 2, lb, (lb + rb) / 2);
        int leftMax = max;
        int leftMin = min;
        buildSegTree(heights, root * 2 + 1, (lb + rb) / 2 + 1, rb);
        max = Math.max(max, leftMax);
        min = Math.min(min, leftMin);
        rngMax[root] = max;
        rngMin[root] = min;
    }

    private void query(int root, int lb, int rb) {
        if (lBndry[root] >= lb && rBndry[root] <= rb) {
            max = rngMax[root];
            min = rngMin[root];
            return;
        }

        if (rb <= (lBndry[root] + rBndry[root]) / 2) {
            query(root * 2, lb, rb);
        } else if (lb > (lBndry[root] + rBndry[root]) / 2) {
            query(root * 2 + 1, lb, rb);
        } else {
            int leftMax, leftMin;
            query(root * 2, lb, rb);
            leftMax = max;
            leftMin = min;
            query(root * 2 + 1, lb, rb);
            max = Math.max(max, leftMax);
            min = Math.min(min, leftMin);
        }
    }

    public void query(int lb, int rb) {
        query(1, lb - 1, rb - 1);
        System.out.println(max - min);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        Segment segment = new Segment();

        while (in.hasNext()) {
            int n = in.nextInt();
            int q = in.nextInt();
            int[] heights = new int[n];
            for (int i = 0; i < n; i++)
                heights[i] = in.nextInt();

            segment.buildSegTree(heights, 1, 0, heights.length - 1);

            while (q-- > 0)
                segment.query(in.nextInt(), in.nextInt());
        }
        in.close();
    }
}
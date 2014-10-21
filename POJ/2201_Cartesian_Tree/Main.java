import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static class CartesianTreeNode {
        int id;
        short mainKey, auxKey;
        CartesianTreeNode parent, left, right;

        public CartesianTreeNode(int id, short mainKey, short auxKey) {
            this.id = id;
            this.mainKey = mainKey;
            this.auxKey = auxKey;
        }
    }

    private final static int MAXN = 50010;
    private static CartesianTreeNode[] nodes, stack;
    private static int total = 0;
    private static int[] parent = new int[MAXN], left = new int[MAXN], right = new int[MAXN];

    private static void buildCartesianTree() {
        Arrays.sort(nodes, 0, total, new Comparator<CartesianTreeNode>() {
            @Override
            public int compare(CartesianTreeNode a, CartesianTreeNode b) {
                return a.mainKey - b.mainKey;
            }
        });

        for (int i = 0, top = -1, k = top; i < total; i++) {
            while (k >= 0 && stack[k].auxKey > nodes[i].auxKey)
                k--;

            if (k != -1) {
                nodes[i].parent = stack[k];
                stack[k].right = nodes[i];
            }
            if (k < top) {
                stack[k + 1].parent = nodes[i];
                nodes[i].left = stack[k + 1];
            }
            stack[++k] = nodes[i];
            top = k;
        }
        stack[0].parent = null;
    }

    private static void output() {
        for (int i = 0; i < total; i++) {
            int id = nodes[i].id;
            CartesianTreeNode p = nodes[i].parent, l = nodes[i].left, r = nodes[i].right;
            parent[id] = p == null ? 0 : p.id + 1;
            left[id] = l == null ? 0 : l.id + 1;
            right[id] = r == null ? 0 : r.id + 1;
        }

        StringBuilder buffer = new StringBuilder("YES\n");
        for (int i = 0; i < total; i++)
            buffer.append(parent[i]).append(' ').append(left[i]).append(' ').append(right[i]).append('\n');
        System.out.print(buffer.toString());
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        String line = bi.readLine();

        total = Integer.parseInt(line);
        nodes = new CartesianTreeNode[total];
        for (int idx = 0; idx < total; idx++) {
            line = bi.readLine();
            String[] numbers = line.split(" ");
            nodes[idx] = new CartesianTreeNode(idx, Short.parseShort(numbers[0]), Short.parseShort(numbers[1]));
        }

        stack = new CartesianTreeNode[total];
        buildCartesianTree();
        output();

        bi.close();
    }
}

/*
 * implementation for fast execution 
 */
class Main2 {
    private final static int HALFN = 30001;
    private final static int MAXN = HALFN * 2;

    private static short[] mainKey, auxKey;
    private static int[] parent, left, right;
    private static int[] sortedNodes = new int[MAXN];
    private static boolean[] accountable = new boolean[MAXN];
    private static int total = 0;

    private static void buildCartesianTree() {

        Arrays.fill(accountable, false);
        for (int i = 1; i <= total; i++) {
            int idx = mainKey[i] + HALFN;
            sortedNodes[idx] = i;
            accountable[idx] = true;
        }

        int[] stack = new int[total];
        for (int i = 0, top = -1, k = top; i < MAXN; i++) {
            if (!accountable[i])
                continue;

            while (k >= 0 && auxKey[stack[k]] > auxKey[sortedNodes[i]])
                k--;

            if (k != -1) {
                parent[sortedNodes[i]] = stack[k];
                right[stack[k]] = sortedNodes[i];
            }
            if (k < top) {
                parent[stack[k + 1]] = sortedNodes[i];
                left[sortedNodes[i]] = stack[k + 1];
            }
            stack[++k] = sortedNodes[i];
            top = k;
        }
        parent[stack[0]] = 0;
    }

    private static void output() {
        StringBuilder buffer = new StringBuilder("YES\n");
        for (int i = 1; i <= total; i++) {
            buffer.append(parent[i]).append(' ').append(left[i]).append(' ').append(right[i]).append('\n');
        }
        System.out.print(buffer.toString());
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        String line = bi.readLine();
        total = Integer.parseInt(line);
        parent = new int[total + 1];
        left = new int[total + 1];
        right = new int[total + 1];
        mainKey = new short[total + 1];
        auxKey = new short[total + 1];

        for (int idx = 1; idx <= total; idx++) {
            line = bi.readLine();
            short k = 0, a = 0, kSign = 1, aSign = 1;
            for (int i = 0, sp = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    if (sp == 0)
                        k = (short) (k * 10 + ch - '0');
                    else
                        a = (short) (a * 10 + ch - '0');
                } else if (ch == ' ') {
                    sp++;
                } else if (ch == '-') {
                    kSign *= (sp == 0) ? -1 : 1;
                    aSign *= (sp == 0) ? 1 : -1;
                }
            }

            mainKey[idx] = (short) (k * kSign);
            auxKey[idx] = (short) (a * aSign);
        }

        buildCartesianTree();
        output();

        bi.close();
    }
}

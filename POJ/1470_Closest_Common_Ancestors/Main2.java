/*
 * Sparse Table algorithm
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class NaryTree {
    public short[][] adjList;
    public short[] childCnt, inDegree;

    public NaryTree(short maxNode) {
        adjList = new short[maxNode][maxNode];
        childCnt = new short[maxNode];
        inDegree = new short[maxNode];
        Arrays.fill(inDegree, (short) -1);
    }

    public void clear() {
        Arrays.fill(childCnt, (short) 0);
        Arrays.fill(inDegree, (short) -1);
    }

    public void addNode(String line) {
        short node = 0, cnt = 0;
        int i = 0;
        for (int p = 0; p < 2; i++) {
            if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                if (p == 0)
                    node = (short) (node * 10 + line.charAt(i) - '0');
                else
                    cnt = (short) (cnt * 10 + line.charAt(i) - '0');

                if (line.charAt(i + 1) < '0' || line.charAt(i + 1) > '9')
                    p++;
            }
        }

        for (short child = 0; cnt > 0; i++) {
            if (line.charAt(i) >= '0' && line.charAt(i) <= '9')
                child = (short) (child * 10 + line.charAt(i) - '0');

            if (child > 0 && (i == line.length() - 1 || line.charAt(i + 1) < '0' || line.charAt(i + 1) > '9')) {
                adjList[node][childCnt[node]++] = child;
                inDegree[child] = (short) (Math.max(inDegree[child], 0) + 1);

                child = 0;
                cnt--;
            }
        }

        inDegree[node] = (short) Math.max(inDegree[node], 0);
    }

    public short getRoot() {
        for (short i = 0; i < inDegree.length; i++)
            if (inDegree[i] == 0)
                return i;
        return 0;
    }
}

class Solution {
    final short MAXN = 901;

    public NaryTree tree;

    private short[] euler, dep, pos;
    private short trailLength;
    private short[][] sTbl;
    private int[] result;

    Solution() {
        tree = new NaryTree(MAXN);

        euler = new short[MAXN * 2];
        dep = new short[MAXN * 2];
        pos = new short[MAXN];
        sTbl = new short[MAXN * 2][(short) (Math.log(MAXN * 2) / Math.log(2)) + 1];

        result = new int[MAXN];
    }

    public void clear() {
        tree.clear();
        trailLength = 0;
        Arrays.fill(result, 0);
    }

    private void dfs(short root, short level) {
        euler[trailLength] = root;
        dep[trailLength++] = level;
        for (short i = 0; i < tree.childCnt[root]; i++) {
            dfs(tree.adjList[root][i], (short) (level + 1));
            euler[trailLength] = root;
            dep[trailLength++] = level;
        }
    }

    private short higher(short a, short b) {
        if (dep[a] < dep[b])
            return a;

        return b;
    }

    public void preProcess() {
        dfs(tree.getRoot(), (short) 1);

        Arrays.fill(pos, trailLength);
        for (short i = 0; i < trailLength; i++) {
            pos[euler[i]] = (short) Math.min(pos[euler[i]], i);
            sTbl[i][0] = i;
        }

        for (short j = 1; j <= Math.log(trailLength) / Math.log(2); j++) {
            for (short i = 0; i + (1 << j) - 1 < trailLength; i++)
                sTbl[i][j] = higher(sTbl[i][j - 1], sTbl[i + (1 << (j - 1))][j - 1]);
        }
    }

    private void query(short l, short r) {
        short k = (short) (Math.log(r - l) / Math.log(2));
        short a = higher(sTbl[l][k], sTbl[r - (1 << k) + 1][k]);
        result[euler[a]]++;
    }

    public int processQueries(String line) {
        int cnt = 0;
        boolean wrtFst = true;
        for (int i = 0, u = 0, v = 0; i < line.length(); i++) {
            if (line.charAt(i) == ')') {
                if (pos[u] > pos[v])
                    query(pos[v], pos[u]);
                else
                    query(pos[u], pos[v]);

                u = v = 0;
                wrtFst = true;
                cnt++;
            } else if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                if ((line.charAt(i - 1) < '0' || line.charAt(i - 1) > '9') && u > 0)
                    wrtFst = false;

                if (wrtFst)
                    u = u * 10 + line.charAt(i) - '0';
                else
                    v = v * 10 + line.charAt(i) - '0';
            }
        }

        return cnt;
    }

    public void output() {
        for (int i = 1; i < result.length; i++) {
            if (result[i] > 0)
                System.out.format("%d:%d\n", i, result[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);

        Solution sol = new Solution();
        while (scn.hasNext()) {
            sol.clear();

            int nodeCnt = Integer.parseInt(scn.nextLine());
            for (int i = 0; i < nodeCnt; i++) {
                String line = scn.nextLine();
                sol.tree.addNode(line);
            }
            sol.preProcess();

            int queryCnt = Integer.parseInt(scn.nextLine());
            for (int i = 0; i < queryCnt;) {
                String line = scn.nextLine();
                i += sol.processQueries(line);
            }

            sol.output();
        }
        scn.close();
    }
}

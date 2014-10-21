/*
 * Tarjan's off-line LCA algorithm
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DisjointSet {
    short[] parent, rank, count;

    DisjointSet(int n) {
        parent = new short[n];
        rank = new short[n];
        count = new short[n];
    }

    void union(short a, short b) {
        a = findSet(a);
        b = findSet(b);
        if (a == b)
            return;

        count[a] = count[a] == 0 ? (short) 1 : count[a];
        count[b] = count[b] == 0 ? (short) 1 : count[b];

        if (rank[a] > rank[b]) {
            parent[b] = a;
            count[a] += count[b];
        } else {
            parent[a] = b;
            if (rank[a] == rank[b])
                rank[b]++;
            count[b] += count[a];
        }
    }

    short findSet(short a) {
        if (parent[a] == 0)
            return a;

        parent[a] = findSet(parent[a]);
        return parent[a];
    }

    void clear() {
        Arrays.fill(parent, (short) 0);
        Arrays.fill(rank, (short) 0);
        Arrays.fill(rank, (short) 0);
    }
}

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
        Pattern ptn1 = Pattern.compile("(\\d+):\\((\\d+)\\)(.*)");
        Matcher mch1 = ptn1.matcher(line);
        if (mch1.find()) {
            short node = (short) Integer.parseInt(mch1.group(1));
            if (Integer.parseInt(mch1.group(2)) > 0) {
                String children = mch1.group(3);
                Pattern ptn2 = Pattern.compile("\\d+");
                Matcher mch2 = ptn2.matcher(children);

                while (mch2.find()) {
                    short child = (short) Integer.parseInt(mch2.group());
                    adjList[node][childCnt[node]++] = child;
                    inDegree[child] = (short) (Math.max(inDegree[child], 0) + 1);
                }
            }

            inDegree[node] = (short) Math.max(inDegree[node], 0);
        }
    }

    public short getRoot() {
        for (short i = 0; i < inDegree.length; i++)
            if (inDegree[i] == 0)
                return i;
        return 0;
    }
}

class Query {
    public short[][] pair;
    public short[] count;

    public Query(short maxNode) {
        pair = new short[maxNode][maxNode];
        count = new short[maxNode];
    }

    public void clear() {
        Arrays.fill(count, (short) 0);
    }

    public int addQuery(String line) {
        int cnt = 0;
        Pattern ptn = Pattern.compile("\\((\\d+)\\D+(\\d+)\\)");
        Matcher mch = ptn.matcher(line);
        while (mch.find()) {
            short u = (short) Integer.parseInt(mch.group(1));
            short v = (short) Integer.parseInt(mch.group(2));

            pair[u][count[u]++] = v;
            pair[v][count[v]++] = u;

            cnt++;
        }
        return cnt;
    }
}

class Solution1 {
    final short MAXN = 901;

    public NaryTree tree;
    public Query query;

    private short[] ancestor;
    private boolean[] visited;
    private DisjointSet ds;
    private int[] result;

    Solution1() {
        tree = new NaryTree(MAXN);
        query = new Query(MAXN);

        ancestor = new short[MAXN];
        visited = new boolean[MAXN];
        ds = new DisjointSet(MAXN);

        result = new int[MAXN];
    }

    public void clear() {
        tree.clear();
        query.clear();

        Arrays.fill(ancestor, (short) 0);
        Arrays.fill(visited, false);
        ds.clear();

        Arrays.fill(result, 0);
    }

    public void lca(short u) {
        ancestor[u] = u;
        for (short i = 0; i < tree.childCnt[u]; i++) {
            short v = tree.adjList[u][i];
            lca(v);
            ds.union(u, v);
            ancestor[ds.findSet(u)] = u;
        }
        visited[u] = true;

        for (short i = 0; i < query.count[u]; i++) {
            short v = query.pair[u][i];
            if (visited[v])
                result[ancestor[ds.findSet(v)]]++;
        }
    }

    public void output() {
        for (int i = 1; i < result.length; i++) {
            if (result[i] > 0)
                System.out.format("%d:%d\n", i, result[i]);
        }
    }
}

/*
 * concise version of Solution1 
 */
class Solution2 {
    final short MAXN = 901;

    public NaryTree tree;
    public Query query;

    private short[] ancestor;
    private boolean[] visited;
    private int[] result;

    Solution2() {
        tree = new NaryTree(MAXN);
        query = new Query(MAXN);

        ancestor = new short[MAXN];
        visited = new boolean[MAXN];

        result = new int[MAXN];
    }

    public void clear() {
        tree.clear();
        query.clear();

        Arrays.fill(ancestor, (short) 0);
        Arrays.fill(visited, false);

        Arrays.fill(result, 0);
    }

    private short find(short x) {
        if (ancestor[x] == x)
            return x;
        short a = find(ancestor[x]);
        ancestor[x] = a;
        return a;
    }

    public void lca(short u) {
        ancestor[u] = u;
        for (short i = 0; i < tree.childCnt[u]; i++) {
            short v = tree.adjList[u][i];
            lca(v);
            ancestor[v] = u;
        }
        visited[u] = true;

        for (short i = 0; i < query.count[u]; i++) {
            short v = query.pair[u][i];
            if (visited[v])
                result[find(v)]++;
        }
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

        Solution2 sol = new Solution2();
        while (scn.hasNext()) {
            sol.clear();

            int nodeCnt = Integer.parseInt(scn.nextLine());
            for (int i = 0; i < nodeCnt; i++) {
                String line = scn.nextLine();
                sol.tree.addNode(line);
            }

            int queryCnt = Integer.parseInt(scn.nextLine());
            for (int i = 0; i < queryCnt;) {
                String line = scn.nextLine();
                i += sol.query.addQuery(line);
            }

            sol.lca(sol.tree.getRoot());
            sol.output();
        }
        scn.close();
    }
}

/*
 * Disjoint-set
 */

import java.io.BufferedInputStream;
import java.util.Scanner;

class DisjointSet {
    int[] parent, rank, count;

    /*
     * 1-Based member. ctor creates n members (1...n) in one batch. So, this
     * class doesn't have a MakeSet method. If x's parent is 0, means x is
     * the representative.
     */
    DisjointSet(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        count = new int[n + 1];
    }

    void union(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a == b)
            return;

        count[a] = count[a] == 0 ? 1 : count[a];
        count[b] = count[b] == 0 ? 1 : count[b];

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

    int findSet(int a) {
        if (parent[a] == 0)
            return a;

        parent[a] = findSet(parent[a]); // two-pass path compression
        return parent[a];
    }

    int getCount(int a) {
        return count[a] == 0 ? 1 : count[a];
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        while (true) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (n == 0 && m == 0)
                break;

            DisjointSet ds = new DisjointSet(n);
            for (int i = 0; i < m; i++) {
                int membersInGroup = scanner.nextInt();
                int prev = scanner.nextInt();
                for (int j = 1; j < membersInGroup; j++) {
                    int curr = scanner.nextInt();
                    ds.union(prev + 1, curr + 1); // input is 0-Based
                    prev = curr;
                }
            }
            System.out.println(ds.getCount(ds.findSet(1)));
        }
        scanner.close();
    }
}

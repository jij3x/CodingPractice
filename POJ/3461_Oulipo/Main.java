/*
 * KMP
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String p = in.readLine();
            String s = in.readLine();
            System.out.println(kmpMatch(s, p));
        }
    }

    private static int kmpMatch(String s, String p) {
        int count = 0, m = 0, i = 0;
        int[] kmpTbl = new int[p.length() + 2];
        preCompute(p, kmpTbl);
        while (m + p.length() <= s.length()) {
            if (s.charAt(m + i) == p.charAt(i)) {
                if (i == p.length() - 1) {
                    count++;
                    m = m + i - kmpTbl[i];
                    i = kmpTbl[i] == -1 ? 0 : kmpTbl[i];
                } else {
                    i++;
                }
            } else {
                m = m + i - kmpTbl[i];
                i = kmpTbl[i] == -1 ? 0 : kmpTbl[i];
            }
        }

        return count;
    }

    private static void preCompute(String p, int[] kmpTbl) {
        kmpTbl[0] = -1;
        kmpTbl[1] = 0;

        int pos = 2, cnd = 0;
        while (pos < p.length()) {
            if (p.charAt(cnd) == p.charAt(pos - 1)) {
                kmpTbl[pos++] = ++cnd;
            } else if (cnd > 0) {
                cnd = kmpTbl[cnd];
            } else {
                pos++;
            }
        }
    }
}

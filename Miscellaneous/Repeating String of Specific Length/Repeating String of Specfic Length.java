public class Solution {
    public List<String> repeatingSubstrings(String S, int n) {
        List<String> result = new ArrayList<String>();
        Set<String> repo = new TreeSet<>();

        HashSet<String> memo = new HashSet<String>();
        for (int i = 0; i <= S.length() - n; i++) {
            String curr = S.substring(i, i + n);
            if (memo.contains(curr))
                repo.add(curr);
            else
                memo.add(curr);
        }

        for (String ss : repo)
            result.add(ss);
        return result;
    }

    // dna contains only 'G', 'A', 'T', 'C'
    public List<String> repeatingSequences(String dna, int n) {
        int[] dnaMap = new int[256];
        dnaMap['A'] = 0;
        dnaMap['G'] = 1;
        dnaMap['T'] = 2;
        dnaMap['C'] = 3;

        char[] revDnaMap = { 'A', 'G', 'T', 'C' };

        List<String> result = new ArrayList<String>();
        PriorityQueue<Integer> repo = new PriorityQueue<Integer>();
        HashSet<Integer> memo = new HashSet<Integer>();

        int rollingHash = 0;
        for (int i = 0; i < n - 1; i++) {
            rollingHash = (rollingHash << 2) | dnaMap[dna.charAt(i)];
        }

        int mask = ~(-1 << (2 * n));
        for (int i = 0; i <= dna.length() - n; i++) {
            rollingHash = (rollingHash << 2) & mask | dnaMap[dna.charAt(i + n - 1)];

            if (memo.contains(rollingHash))
                repo.add(rollingHash);
            else
                memo.add(rollingHash);
        }

        for (Integer sub : repo) {
            StringBuilder buf = new StringBuilder();
            for (int i = 0; i < n; i++) {
                buf.insert(0, revDnaMap[sub & 3]);
                sub >>= 2;
            }
            result.add(buf.toString());
        }
        return result;
    }
}

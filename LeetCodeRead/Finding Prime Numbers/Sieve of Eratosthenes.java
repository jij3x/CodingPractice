public class Solution {
    public ArrayList<Integer> findPrimes(int n) {
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                for (int c = 2; c * i <= n; c++)
                    primes[c * i] = false;
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            if (primes[i])
                result.add(i);
        }
        return result;
    }

    public ArrayList<Integer> optFindPrimes(int n) {
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (primes[i]) {
                for (int c = 0; i * i + c * i <= n; c++)
                    primes[i * i + c * i] = false;
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            if (primes[i])
                result.add(i);
        }
        return result;
    }
}
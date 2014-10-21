public class Solution {
    public ArrayList<Integer> primeFactors(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int sqrt = (int) Math.sqrt(n);
        for (int factor = 2; factor <= sqrt; factor++) {
            while (n % factor == 0) {
                n /= factor;
                result.add(factor);
            }
        }
        return result;
    }
}
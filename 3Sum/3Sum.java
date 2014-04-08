public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1])
                continue;

            int m = i + 1, n = num.length - 1;
            while (m < n) {
                if (num[m] + num[n] < -num[i]) {
                    m++;
                } else if (num[m] + num[n] > -num[i]) {
                    n--;
                } else {
                    ArrayList<Integer> r = new ArrayList<Integer>(Arrays.asList(num[i], num[m++], num[n--]));
                    result.add(r);

                    while (m < n && num[m] == num[m - 1])
                        m++;

                    while (m < n && num[n] == num[n + 1])
                        n--;
                }
            }
        }

        return result;
    }
}

public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1])
                continue;

            int start = i + 1, end = num.length - 1;
            while (start < end) {
                if (num[start] + num[end] > -num[i]) {
                    end--;
                } else if (num[start] + num[end] < -num[i]) {
                    start++;
                } else {
                    result.add(new ArrayList<Integer>(Arrays.asList(num[i], num[start++], num[end--])));

                    while (start < end && num[start] == num[start - 1])
                        start++;

                    while (start < end && num[end] == num[end + 1])
                        end--;
                }
            }
        }

        return result;
    }
}

public class Solution {
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= num.length - 4; i++) {
            if (i > 0 && num[i] == num[i - 1])
                continue;

            for (int j = i + 1; j <= num.length - 3; j++) {
                if (j > i + 1 && num[j] == num[j - 1])
                    continue;

                int start = j + 1, end = num.length - 1;
                while (start < end) {
                    if (num[i] + num[j] + num[start] + num[end] == target) {
                        Integer[] r = { num[i], num[j], num[start++], num[end--] };
                        result.add(new ArrayList<Integer>(Arrays.asList(r)));

                        while (start < end && num[start] == num[start - 1])
                            start++;
                        while (start < end && num[end] == num[end + 1])
                            end--;
                    } else if (num[i] + num[j] + num[start] + num[end] > target) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        return result;
    }
}

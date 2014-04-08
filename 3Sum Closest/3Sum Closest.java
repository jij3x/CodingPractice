public class Solution {
    public int threeSumClosest(int[] num, int target) {
        int closest = num[0] + num[1] + num[2];
        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum == target)
                    return target;

                if (Math.abs(target - sum) < Math.abs(target - closest))
                    closest = sum;

                if (sum > target)
                    end--;
                else
                    start++;
            }
        }

        return closest;
    }
}

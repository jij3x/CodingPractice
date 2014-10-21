public class Solution {
    public int threeSumClosest(int[] num, int target) {
        int closest = num[0] + num[1] + num[2];
        Arrays.sort(num);
        
        for (int i = 0; i < num.length - 2; i++) {
            for (int start = i + 1, end = num.length - 1; start < end;) {
                int sum = num[i] + num[start] + num[end];
                if (Math.abs(sum - target) < Math.abs(closest - target))
                    closest = sum;

                if (sum == target)
                    return target;
                else if (sum < target)
                    start++;
                else
                    end--;
            }
        }
        return closest;
    }
}

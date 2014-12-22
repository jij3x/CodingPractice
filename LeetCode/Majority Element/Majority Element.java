public class Solution {
    public int majorityElement(int[] num) {
        int majority = num[0];
        for (int i = 1, counter = 1; i < num.length; i++) {
            if (num[i] == majority)
                counter++;
            else if (counter == 1)
                majority = num[i];
            else
                counter--;
        }
        return majority;
    }
}
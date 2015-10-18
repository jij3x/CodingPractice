public class Solution {
    public long sum(int[] nums) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];
        return sum;
    }

    public long mtSum(int[] nums) throws InterruptedException {
        int n = 2;
        long[] sums = new long[n];
        MultithreadingSum sum = new MultithreadingSum(nums, sums, n);
        return sum.doSum();
    }
}

class MultithreadingSum {
    private int[] nums;
    private long[] sums;
    private int n;

    public MultithreadingSum(int[] nums, long[] sums, int n) {
        this.nums = nums;
        this.sums = sums;
        this.n = n;
    }

    public class sumThread implements Runnable {
        private int offset, length;

        public sumThread(int offset, int length) {
            this.offset = offset;
            this.length = length;
        }

        @Override
        public void run() {
            int pos = offset / length;
            sums[pos] = 0;
            for (int i = offset; i < offset + length; i++) {
                sums[pos] += nums[i];
            }
        }
    }

    public long doSum() throws InterruptedException {
        int seg = nums.length / n;
        Thread[] pool = new Thread[n];
        for (int i = 0; i < n; i++) {
            pool[i] = new Thread(new sumThread(i * seg, seg));
            pool[i].start();
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            pool[i].join();
            sum += sums[i];
        }
        return sum;
    }
}
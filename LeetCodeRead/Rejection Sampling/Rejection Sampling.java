public class Solution {
    private int rand7() {
        Random rand = new Random();
        return rand.nextInt(7) + 1;
    }

    public int rand10() {
        int rand = 50;
        while (rand > 40) {
            rand = rand7() * rand7();
        }
        return (rand - 1) % 10 + 1;
    }

    public int rand10_opt() {
        int a, b, i;
        while (true) {
            a = rand7();
            b = rand7();
            i = a * b;
            if (i <= 40)
                return (i - 1) % 10 + 1;

            a = i - 40;
            b = rand7();
            i = a * b;
            if (i <= 60)
                return (i - 1) % 10 + 1;

            a = i - 60;
            b = rand7();
            i = a * b;
            if (i < 20)
                return (i - 1) % 10 + 1;
        }
    }
}

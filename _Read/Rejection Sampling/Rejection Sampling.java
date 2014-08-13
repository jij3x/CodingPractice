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

    public int optRand10() {
        
        return 0;
    }
}
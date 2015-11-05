public class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] map1 = new int[10], map2 = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            map1[secret.charAt(i) - '0']++;
            map2[guess.charAt(i) - '0']++;
            if (secret.charAt(i) == guess.charAt(i))
                bulls++;
        }

        for (int i = 0; i < 10; i++) {
            cows += Math.min(map1[i], map2[i]);
        }
        cows -= bulls;
        return bulls + "A" + cows + "B";
    }
}
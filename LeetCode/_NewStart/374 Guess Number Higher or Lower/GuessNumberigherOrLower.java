/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
  public int guessNumber(int n) {
    for (int left = 1, right = n; left <= right;) {
      int mid = left + (right - left) / 2, g = guess(mid);
      if (g < 0) {
        right = mid - 1;
      } else if (g > 0) {
        left = mid + 1;
      } else {
        return mid;
      }
    }
    return 0;
  }
}
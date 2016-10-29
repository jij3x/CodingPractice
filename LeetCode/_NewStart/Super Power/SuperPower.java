public class Solution {
  public int superPow(int a, int[] b) {
    int result = 1;
    for (int base = a % 1337, i = b.length - 1; i >= 0; i--) {
      for (int j = 0; j < b[i]; j++) {
        result = result * base % 1337;
      }

      for (int j = 0, t = base; j < 9; j++) {
        base = base * t % 1337;
      }
    }
    return result;
  }
}

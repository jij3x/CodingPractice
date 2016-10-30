public class Solution {
  public int getSum(int a, int b) {
    int sum = a ^ b;
    int carry = (a & b) << 1;
    if (carry == 0) {
      return sum;
    }
    return getSum(sum, carry);
  }
}

public class Solution1 {
  public int getSum(int a, int b) {
    while (b != 0) {
      int carry = (a & b) << 1;
      a ^= b;
      b = carry;
    }
    return a;
  }
}

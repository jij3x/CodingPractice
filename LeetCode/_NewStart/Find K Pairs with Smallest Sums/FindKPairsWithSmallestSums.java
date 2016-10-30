public class Solution {
  /*
   * nums1 = {1, 2, 3, 4, 5}
   * nums2 = {2, 3, 4, 5}
   * 
   *        xs ->
   *        2  3  4  5
   *      +-------------
   * y  1 | 3  4  5  6
   * |  2 | 4  5  6  7 
   * v  3 | 5  6  7  8
   *    4 | 6  7  8  9
   *    5 | 7  8  9  10
   */
  public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<int[]> result = new ArrayList<>();
    int y = 0;
    int[] xs = new int[nums1.length];
    for (int i = 0; i < Math.min(k, nums1.length * nums2.length); i++) {
      result.add(new int[] { nums1[y], nums2[xs[y]++] });
      for (int yy = 0, min = nums1[nums1.length - 1] + nums2[nums2.length - 1]; yy < nums1.length; yy++) {
        if (xs[yy] < nums2.length && nums1[yy] + nums2[xs[yy]] <= min) {
          min = nums1[yy] + nums2[xs[yy]];
          y = yy;
        }
      }
    }
    return result;
  }
}

public class Solution2 {
  public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<int[]> result = new ArrayList<>();
    for (int i = 0; i < nums1.length; i++) {
      for (int j = 0; j < nums2.length; j++) {
        result.add(new int[] { nums1[i], nums2[j] });
      }
    }

    Collections.sort(result, (a, b) -> a[0] + a[1] - b[0] - b[1]);
    return result.subList(0, Math.min(result.size(), k));
  }
}

public class Solution3 {
  public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<int[]> result = new ArrayList<>();
    for (int i = 0; i < nums1.length; i++) {
      for (int j = 0; j < nums2.length; j++) {
        result.add(new int[] { nums1[i], nums2[j] });
      }
    }

    Collections.sort(result, new Comparator<int[]>() {
      @Override
      public int compare(int[] a1, int[] a2) {
        return a1[0] + a1[1] - a2[0] - a2[1];
      }
    });
    return result.subList(0, Math.min(result.size(), k));
  }
}
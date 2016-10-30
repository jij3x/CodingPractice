public class Solution1 {
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


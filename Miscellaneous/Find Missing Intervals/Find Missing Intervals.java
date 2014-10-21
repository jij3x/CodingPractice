/*
 * public class Interval {
 *     int start, end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class Solution {
    public String findMissingInterval(int[] A) {
        List<Interval> result = new ArrayList<Interval>();
        if (A.length == 0)
            result.add(new Interval(0, 99));

        int prev = -1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != prev + 1) {
                Interval missing = new Interval(prev + 1, A[i] - 1);
                merge(result, missing);
            }
            prev = A[i];
        }

        if (A.length > 0 && A[A.length - 1] != 99)
            merge(result, new Interval(prev + 1, 99));

        StringBuilder buf = new StringBuilder();
        for (Interval i : result) {
            buf.append(buf.length() == 0 ? "" : ", ");
            buf.append(i.start);
            buf.append(i.end == i.start ? "" : "-" + Integer.toString(i.end));
        }
        return buf.toString();
    }

    private void merge(List<Interval> group, Interval single) {
        if (!group.isEmpty() && single.start == group.get(group.size() - 1).end + 1)
            group.get(group.size() - 1).end = single.end;
        else
            group.add(single);
    }
}

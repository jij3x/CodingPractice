/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        for (Interval curr : intervals) {
            if (curr.start > newInterval.end) {
                result.add(newInterval);
                newInterval = curr;
            } else if (curr.end < newInterval.start) {
                result.add(curr);
            } else {
                newInterval.start = Math.min(newInterval.start, curr.start);
                newInterval.end = Math.max(newInterval.end, curr.end);
            }
        }
        result.add(newInterval);
        return result;
    }
}

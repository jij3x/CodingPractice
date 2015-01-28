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
    class myComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval int1, Interval int2) {
            return int1.start < int2.start ? -1 : (int1.start > int2.start ? 1 : 0);
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new myComparator());
        ArrayList<Interval> result = new ArrayList<Interval>();
        for (Interval curr : intervals) {
            Interval last = result.isEmpty() ? null : result.get(result.size() - 1);
            if (!result.isEmpty() && curr.start <= last.end)
                last.end = Math.max(last.end, curr.end);
            else
                result.add(curr);
        }
        return result;
    }

    public ArrayList<Interval> merge2(ArrayList<Interval> intervals) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        Collections.sort(intervals, new myComparator());
        for (int i = 0; i < intervals.size();) {
            Interval last = new Interval(intervals.get(i).start, intervals.get(i).end);
            result.add(last);
            for (i++; i < intervals.size() && intervals.get(i).start <= last.end; i++)
                last.end = Math.max(last.end, intervals.get(i).end);
        }
        return result;
    }
}


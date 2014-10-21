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
        public int compare(Interval i1, Interval i2) {
            return i1.start == i2.start ? 0 : (i1.start > i2.start ? 1 : -1);
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new myComparator());
        ArrayList<Interval> result = new ArrayList<Interval>();
        for (Interval i : intervals) {
            if (result.isEmpty()) {
                result.add(i);
                continue;
            }
            Interval last = result.get(result.size() - 1);
            if (i.start <= last.end)
                last.end = Math.max(i.end, last.end);
            else
                result.add(new Interval(i.start, i.end));
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


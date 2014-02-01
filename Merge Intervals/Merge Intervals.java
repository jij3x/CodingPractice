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
	class intervalComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval i1, Interval i2) {
			return i1.start == i2.start ? 0 : (i1.start > i2.start ? 1 : -1);
		}
	}

	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		ArrayList<Interval> result = new ArrayList<Interval>();
		if (intervals.size() == 0)
			return result;

		Collections.sort(intervals, new intervalComparator());
		Interval inAir = new Interval(intervals.get(0).start, intervals.get(0).end);
		for (int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if (curr.start > inAir.end) {
				result.add(inAir);
				inAir = new Interval(curr.start, curr.end);
			} else {
				inAir.end = Math.max(inAir.end, curr.end);
			}
		}
		result.add(inAir);
		return result;
	}

	public ArrayList<Interval> merge2(ArrayList<Interval> intervals) {
		ArrayList<Interval> result = new ArrayList<Interval>();
		Collections.sort(intervals, new intervalComparator());

		for (int i = 0; i < intervals.size();) {
			Interval last = new Interval(intervals.get(i).start, intervals.get(i).end);
			result.add(last);
			for (i++; i < intervals.size() && intervals.get(i).start <= last.end; i++)
				last.end = Math.max(last.end, intervals.get(i).end);
		}
		return result;
	}
}

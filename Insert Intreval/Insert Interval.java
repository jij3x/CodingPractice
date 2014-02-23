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
	public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
		ArrayList<Interval> result = new ArrayList<Interval>();

		int index = 0;
		for (; index < intervals.size(); index++) {
			if (intervals.get(index).end >= newInterval.start)
				break;

			result.add(intervals.get(index));
		}

		Interval mergeInt = new Interval(newInterval.start, newInterval.end);
		for (; index < intervals.size(); index++) {
			if (intervals.get(index).start > newInterval.end)
				break;

			mergeInt.start = Math.min(mergeInt.start, intervals.get(index).start);
			mergeInt.end = Math.max(mergeInt.end, intervals.get(index).end);
		}
		result.add(mergeInt);

		while (index < intervals.size())
			result.add(intervals.get(index++));

		return result;
	}
}
public class Solution {
	public int sqrt(int x) {
		int start = 1, end = x;
		while (start <= end) {
			long middle = ((long) start + (long) end) / 2;
			if (middle * middle == x)
				return (int) middle;
			else if (middle * middle > x)
				end = (int) middle - 1;
			else
				start = (int) middle + 1;
		}
		return end;
	}
}
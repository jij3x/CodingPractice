/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
	public int maxPoints(Point[] points) {
		int finalMax = 0;
		for (int i = 0; i < points.length; i++) {
			int max = 0;
			int same = 1;
			HashMap<String, Integer> pointsMap = new HashMap<String, Integer>();
			for (int j = i + 1; j < points.length; j++) {
				int x = points[i].x - points[j].x;
				int y = points[i].y - points[j].y;

				if (x == 0 && y == 0) {
					same++;
				} else {
					int c = gcd(x, y);
					x /= c;
					y /= c;

					String point = Integer.toString(x) + "," + Integer.toString(y);
					int occurrence = pointsMap.get(point) == null ? 0 : pointsMap.get(point);
					max = Math.max(max, ++occurrence);
					pointsMap.put(point, occurrence);
				}
			}
			finalMax = Math.max(finalMax, max + same);
		}

		return finalMax;
	}

	private int gcd(int a, int b) {
		return a == 0 ? b : a / Math.abs(a) * Math.abs(gcd(b % a, a));
	}
}

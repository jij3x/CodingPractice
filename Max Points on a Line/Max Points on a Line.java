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
            int max = 0, same = 1;
            HashMap<String, Integer> ptMap = new HashMap<String, Integer>();
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
                    int cnt = ptMap.get(point) == null ? 0 : ptMap.get(point);
                    max = Math.max(max, ++cnt);
                    ptMap.put(point, cnt);
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

class Solution2 {
    class Delta {
        int a, b;

        Delta(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int hashCode() {
            if (a == 0)
                return Integer.MAX_VALUE;
            else if (b == 0)
                return Double.valueOf(0.0).hashCode();

            return Double.valueOf((double) b / a).hashCode();
        }

        public boolean equals(Object obj) {
            Delta d = (Delta) obj;
            return (double) this.a * d.b == (double) this.b * d.a;
        }
    }

    public int maxPoints(Point[] points) {
        int finalMax = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Delta, Integer> deltasMap = new HashMap<Delta, Integer>();
            int max = 0, same = 1;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    same++;
                } else {
                    Delta delta = new Delta(points[i].x - points[j].x, points[i].y - points[j].y);
                    deltasMap.put(delta, deltasMap.containsKey(delta) ? deltasMap.get(delta) + 1 : 1);

                    max = Math.max(max, deltasMap.get(delta));
                }
            }
            finalMax = Math.max(finalMax, max + same);
        }

        return finalMax;
    }
}

class Solution3 {
    public int maxPoints(Point[] points) {
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int localMax = 0, same = 1, perp = 1;
            HashMap<Double, Integer> memo = new HashMap<Double, Integer>();
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].y == points[j].y && points[i].x == points[j].x) {
                    same++;
                } else if (points[i].y == points[j].y) {
                    perp++;
                } else {
                    double slope = (double) (points[i].x - points[j].x) / (double) (points[i].y - points[j].y);
                    slope = slope == -0.0 ? 0.0 : slope;
                    int cnt = memo.containsKey(slope) ? memo.get(slope) + 1 : 1;
                    memo.put(slope, cnt);
                }
            }

            for (Integer cnt : memo.values()) {
                localMax = Math.max(localMax, cnt);
            }
            localMax = Math.max(localMax + same, perp);
            max = Math.max(max, localMax);
        }
        return max;
    }
}

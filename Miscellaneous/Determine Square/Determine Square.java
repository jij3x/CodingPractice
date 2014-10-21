class Point {
    int x, y;
}

public class Solution {
    boolean isSquare(Point[] points) {
        HashSet<Long> dists = new HashSet<Long>();
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                int deltaX = points[i].x - points[j].x;
                int deltaY = points[i].y - points[j].y;
                dists.add((long) deltaX * deltaX + (long) deltaY * deltaY);
            }
        }

        Iterator<Long> iter = dists.iterator();
        long[] d = new long[dists.size()];
        int i = 0;
        while (iter.hasNext())
            d[i++] = iter.next();

        if (d.length == 2 && (d[0] * 2 != d[1] || d[1] * 2 != d[0]))
            return true;

        return false;
    }
}
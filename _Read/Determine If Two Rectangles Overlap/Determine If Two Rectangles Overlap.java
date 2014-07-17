public class Solution {
    class Rectangle {
        int x1, y1, x2, y2;

        Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y2;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public boolean overlap(Rectangle r1, Rectangle r2) {
        return !(r2.x1 > r1.x2 || r2.y1 > r1.y2 || r2.x2 < r1.x1 || r2.y2 < r1.y1);
    }
}
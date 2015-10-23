public class Vector2D {

    private List<List<Integer>> v;
    private int x, y;

    public Vector2D(List<List<Integer>> vec2d) {
        v = vec2d;
        y = x = 0;
    }

    public int next() {
        while (x == v.get(y).size()) {
            x = 0;
            y++;
        }
        return v.get(y).get(x++);
    }

    public boolean hasNext() {
        if (y == v.size())
            return false;
        
        int xx = x, yy = y;
        while (xx == v.get(yy).size()) {
            xx = 0;
            if (++yy == v.size())
                return false;
        }
        return true;
    }
}


class Vector2D2 {

    private List<List<Integer>> v;
    private int x, y;

    public Vector2D(List<List<Integer>> vec2d) {
        v = vec2d;
        y = x = -1;
    }

    public int next() {
        hasNext();
        return v.get(y).get(x++);
    }

    public boolean hasNext() {
        while ((x == -1 || x == v.get(y).size()) && y < v.size() - 1) {
            y++;
            x = 0;
        }
        return x != -1 && x < v.get(y).size();
    }
}


class Vector2D3 {

    private Iterator<List<Integer>> ity;
    private Iterator<Integer> itx;

    public Vector2D(List<List<Integer>> vec2d) {
        ity = vec2d.iterator();
    }

    public int next() {
        hasNext();
        return itx.next();
    }

    public boolean hasNext() {
        while ((itx == null || !itx.hasNext()) && ity.hasNext())
            itx = ity.next().iterator();
        return itx != null && itx.hasNext();
    }
}
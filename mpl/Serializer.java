import java.util.List;

public class Serializer {
    public static String serialize_int(int n) {
        return Integer.toString(n);
    }

    public static String serializeIntArray(int[] array, int n) {
        if (n == 0 || array == null || array.length == 0)
            return "[]";
        StringBuffer r = new StringBuffer("[");
        r.append(Integer.toString(array[0]));
        for (int i = 1; i < n; i++) {
            r.append(",").append(Integer.toString(array[i]));
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeIntList(List<Integer> list, int n) {
        if (n == 0 || list == null || list.size() == 0)
            return "[]";
        StringBuffer r = new StringBuffer("[");
        r.append(Integer.toString(list.get(0)));
        for (int i = 1; i < n; i++) {
            r.append(",").append(Integer.toString(list.get(i)));
        }
        r.append("]");
        return r.toString();
    }

    /*
     * deserialize int[]
     */
    private int priIntArrayI;
    private int[] priIntArray;

    public void allocIntArray(int size) {
        priIntArrayI = 0;
        priIntArray = new int[size];
    }

    public void intArrayAdd(int n) {
        priIntArray[priIntArrayI++] = n;
    }

    public int[] getIntArray() {
        return priIntArray;
    }
    /* end deserialize int[] */
}

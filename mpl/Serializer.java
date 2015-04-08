import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    public static String serializeInt(int n) {
        return Integer.toString(n);
    }

    public static String serializeIntArray(int[] array) {
        if (array == null || array.length == 0)
            return "[]";

        StringBuffer r = new StringBuffer("[");
        r.append(Integer.toString(array[0]));
        for (int i = 1; i < array.length; i++) {
            r.append(",").append(Integer.toString(array[i]));
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeIntVector(ArrayList<Integer> vector) {
        if (vector == null || vector.size() == 0)
            return "[]";

        StringBuffer r = new StringBuffer("[");
        r.append(Integer.toString(vector.get(0)));
        for (int i = 1; i < vector.size(); i++) {
            r.append(",").append(Integer.toString(vector.get(i)));
        }
        r.append("]");
        return r.toString();
    }

    public static int deserializeInt(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    public static int[] deserializeIntArray(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        int[] array = new int[size];
        for (int j = 0; j < size; j++) {
            tokenizer.nextToken();
            array[j] = (int) tokenizer.nval;
        }
        return array;
    }

    public static List<Integer> deserializeIntVector(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        List<Integer> vector = new ArrayList<Integer>();
        for (int j = 0; j < size; j++) {
            tokenizer.nextToken();
            vector.add((int) tokenizer.nval);
        }
        return vector;
    }
}

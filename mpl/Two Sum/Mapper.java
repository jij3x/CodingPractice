import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;

class Mapper {
    public int mapInt() throws IOException {
        InputStream inputStream = new FileInputStream("intMapper.test");
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        // |Int
        tokenizer.nextToken();
        int $param = (int) tokenizer.nval;
        // |Int

        return $param;
    }

    public int[] mapIntArray() throws IOException {
        InputStream inputStream = new FileInputStream("intArrayMapper.test");
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        // |IntArray
        tokenizer.nextToken();
        int $size = (int) tokenizer.nval;
        Serializer ser = new Serializer();
        ser.allocIntArray($size);
        for (int j = 0; j < $size; j++) {
            tokenizer.nextToken();
            ser.intArrayAdd((int) tokenizer.nval);
        }
        int[] $param = ser.getIntArray();
        // |IntArray

        return $param;
    }
}

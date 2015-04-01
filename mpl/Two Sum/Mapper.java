import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;

class Mapper {
    public int mapInt(String fileName) throws IOException {
        {
            InputStream inputStream = new FileInputStream(fileName);
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            StreamTokenizer tokenizer = new StreamTokenizer(reader);

            // |:int
            tokenizer.nextToken();
            int _PARAM_ = (int) tokenizer.nval;
            // |:int

            return _PARAM_;
        }
    }

    public int[] mapIntArray(String fileName) throws IOException {
        {
            InputStream inputStream = new FileInputStream(fileName);
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            StreamTokenizer tokenizer = new StreamTokenizer(reader);

            // |:intArray
            tokenizer.nextToken();
            int _SIZE_ = (int) tokenizer.nval;
            Serializer ser = new Serializer();
            ser.allocIntArray(_SIZE_);
            for (int j = 0; j < _SIZE_; j++) {
                tokenizer.nextToken();
                ser.intArrayAdd((int) tokenizer.nval);
            }
            int[] _PARAM_ = ser.getIntArray();
            // |:intArray

            return _PARAM_;
        }
    }
}

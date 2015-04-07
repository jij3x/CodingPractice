import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;

public class Driver {
    public static void main(String[] args) throws IOException {
        PrintWriter printWriter = new PrintWriter(System.out, true);
        Reader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        tokenizer.nextToken();
        int totalCases = (int) tokenizer.nval;
        for (int i = 0; i < totalCases; i++) {

            // param deserialization code inject here

            // solution invoking code inject here

            // result serialization code inject here

        }
        printWriter.close();
    }
}

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
        PrintWriter errorWriter = new PrintWriter(System.err, true);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        tokenizer.nextToken();
        int totalCases = (int) tokenizer.nval;
        for (int i = 0; i < totalCases; i++) {

            UndirectedGraphNode _PARAM_0_ = Serializer.deserializeIntUDGraph(tokenizer);


            UndirectedGraphNode _RETURN_ = (new Solution()).cloneGraph(_PARAM_0_);

            String _OP_RETURN_0_ =  Helper.checkDeepClonedIntUDGraph(_PARAM_0_, _RETURN_);

            printWriter.println(Serializer.serializeIntUDGraph(_RETURN_));

            errorWriter.println(Serializer.serializeText(_OP_RETURN_0_));

        }
        printWriter.close();
        errorWriter.close();
    }
}


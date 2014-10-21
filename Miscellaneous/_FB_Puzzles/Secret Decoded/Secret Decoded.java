import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		String line = buf.readLine();
		line = buf.readLine(); // discard "//dict"

		ArrayList<String> dict = new ArrayList<String>();
		ArrayList<String> secrets = new ArrayList<String>();
		boolean isSecret = false;
		while (line != null && !line.isEmpty()) {
			if (line.equals("//secret"))
				isSecret = true;
			else if (isSecret)
				secrets.add(line);
			else
				dict.add(line);

			line = buf.readLine();
		}

		HashMap<Character, Character> codeBook = new HashMap<Character, Character>();
		for (String s : secrets) {
			StringTokenizer st = new StringTokenizer(s);
			ArrayList<String> encryptedWords = new ArrayList<String>();
			while (st.hasMoreElements()) {
				encryptedWords.add((String) st.nextElement());
			}
			ArrayList<String> decryptedWords = new ArrayList<String>();

			dfsDecrypt(encryptedWords, 0, decryptedWords, dict, codeBook);
			String plainText = "";
			for (String d : decryptedWords) {
				plainText = " " + d + plainText;
			}
			System.out.println(s + " =" + plainText);
		}
	}

	private static boolean dfsDecrypt(ArrayList<String> encryptedWords, int start, ArrayList<String> decryptedWords,
			ArrayList<String> dict, HashMap<Character, Character> codeBook) {
		if (start >= encryptedWords.size())
			return true;

		for (int i = 0; i < dict.size(); i++) {
			HashMap<Character, Character> newCodeBook = new HashMap<Character, Character>(codeBook);
			if (patternMatch(dict.get(i), encryptedWords.get(start), newCodeBook)
					&& dfsDecrypt(encryptedWords, start + 1, decryptedWords, dict, newCodeBook)) {
				codeBook = newCodeBook;
				String enc = encryptedWords.get(start);
				String dec = "";
				for (int j = 0; j < enc.length(); j++)
					dec += codeBook.get(enc.charAt(j));

				decryptedWords.add(dec); // result (decrypted word list) will be in reversed order!
				return true;
			}
		}

		return false;
	}

	private static boolean patternMatch(String s, String t, HashMap<Character, Character> codeBook) {
		if (s.length() != t.length())
			return false;

		for (int i = 0; i < s.length() - 1; i++) {
			for (int j = i + 1; j < s.length(); j++) {
				if ((s.charAt(i) == s.charAt(j)) != (t.charAt(i) == t.charAt(j)))
					return false;
			}
		}

		for (int i = 0; i < t.length(); i++) {
			char code = t.charAt(i);
			if (codeBook.get(code) == null || codeBook.get(code) == s.charAt(i))
				codeBook.put(code, s.charAt(i));
			else
				return false;
		}

		return true;
	}
}

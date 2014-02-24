public class Solution {
	public ArrayList<String> fullJustify(String[] words, int L) {
		ArrayList<String> result = new ArrayList<String>();
		int start = 0;
		for (int i = 1, spaceLeft = L - words[0].length(); i < words.length; i++) {
			if (spaceLeft - words[i].length() - 1 < 0) {
				result.add(lineJustify(words, L, start, i - 1));
				spaceLeft = L - words[i].length();
				start = i;
			} else {
				spaceLeft -= words[i].length() + 1;
			}
		}
		result.add(lastLineJustify(words, L, start));
		return result;
	}

	private String lineJustify(String[] words, int L, int start, int end) {
		int totalPads = L;
		for (int i = start; i <= end; i++)
			totalPads -= words[i].length();

		String line = words[start++];
		for (int i = start; i <= end; i++) {
			int padCount = totalPads / (end + 1 - i) + (totalPads % (end + 1 - i) == 0 ? 0 : 1);
			totalPads -= padCount;
			for (int j = 0; j < padCount; j++)
				line += ' ';
			line += words[i];
		}

		// pad whitespace, in case of only one word.
		for (int i = line.length(); i < L; i++)
			line += " ";
		return line;
	}

	private String lastLineJustify(String[] words, int L, int start) {
		String line = "";
		for (int i = start; i < words.length; i++)
			line += line.isEmpty() ? words[i] : " " + words[i];

		for (int i = line.length(); i < L; i++)
			line += " ";
		return line;
	}
}
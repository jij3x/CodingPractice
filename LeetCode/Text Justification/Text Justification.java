public class Solution {
    public List<String> fullJustify(String[] words, int L) {
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

        StringBuilder line = new StringBuilder(words[start++]);
        for (int i = start; i <= end; i++) {
            int padCount = totalPads / (end + 1 - i) + (totalPads % (end + 1 - i) == 0 ? 0 : 1);
            totalPads -= padCount;
            for (int j = 0; j < padCount; j++)
                line.append(" ");
            line.append(words[i]);
        }

        // pad whitespace, in case of only one word.
        for (int i = line.length(); i < L; i++)
            line.append(" ");
        return line.toString();
    }

    private String lastLineJustify(String[] words, int L, int start) {
        StringBuilder line = new StringBuilder();
        for (int i = start; i < words.length; i++)
            line.append(line.length() == 0 ? "" : " ").append(words[i]);

        for (int i = line.length(); i < L; i++)
            line.append(" ");
        return line.toString();
    }
}

class Solution2 {
    public List<String> fullJustify(String[] words, int L) {
        ArrayList<String> result = new ArrayList<String>();
        int left = 0;
        for (int i = 1, len = words[0].length(); i < words.length; i++) {
            if (len + words[i].length() + (i - left) > L) {
                StringBuilder line = new StringBuilder();
                for (int j = left, spCnt = L - len; j < i; j++) {
                    line.append(words[j]);

                    int sp = j == i - 1 ? spCnt : spCnt / (i - j - 1) + ((spCnt % (i - j - 1)) > 0 ? 1 : 0);
                    for (int k = sp; k > 0; k--)
                        line.append(' ');
                    spCnt -= sp;
                }
                result.add(line.toString());

                left = i;
                len = words[i].length();
            } else {
                len += words[i].length();
            }
        }

        StringBuilder line = new StringBuilder(words[left++]);
        while (left < words.length)
            line.append(' ').append(words[left++]);
        for (int i = line.length(); i < L; i++)
            line.append(' ');
        result.add(line.toString());
        return result;
    }
}

class Solution3 {
    public List<String> fullJustify(String[] words, int L) {
        ArrayList<String> result = new ArrayList<String>();
        for (int start = 0, k = 0, lineLen = 0; start < words.length; start += k + 1) {
            for (k = 0, lineLen = words[start].length(); start + k + 1 < words.length
                    && k + 1 + lineLen + words[start + k + 1].length() <= L; k++) {
                lineLen += words[start + k + 1].length();
            }

            StringBuilder line = new StringBuilder(words[start]);
            for (int i = 1, sp = L - lineLen; i <= k; i++) {
                int padding = (start + k == words.length - 1 ? 1 : (sp / (k - i + 1) + (sp % (k - i + 1) == 0 ? 0 : 1)));
                sp -= padding;
                for (int j = 0; j < padding; j++) {
                    line.append(" ");
                }
                line.append(words[start + i]);
            }

            for (int i = line.length(); i < L; i++) {
                line.append(" ");
            }
            result.add(line.toString());
        }
        return result;
    }
}

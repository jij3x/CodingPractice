public class Solution {
    public String convert(String s, int nRows) {
        if (nRows == 1)
            return s;

        StringBuilder[] buff = new StringBuilder[nRows];
        for (int i = 0; i < nRows; i++)
            buff[i] = new StringBuilder();

        for (int i = 0, row = 0, step = 1; i < s.length(); i++) {
            buff[row].append(s.charAt(i));
            row += step;
            if (row == nRows) {
                step = -1;
                row = nRows - 2;
            }
            if (row == -1) {
                step = 1;
                row = 1;
            }
        }

        for (int i = 1; i < nRows; i++)
            buff[0].append(buff[i]);
        return buff[0].toString();
    }
}

class Solution2 {
    public String convert(String s, int nRows) {
        if (nRows == 1)
            return s;

        StringBuilder[] buff = new StringBuilder[nRows];
        for (int i = 0; i < nRows; i++)
            buff[i] = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int row = i % (nRows * 2 - 2);
            if (row >= nRows)
                row = nRows - (row - nRows + 2);

            buff[row].append(s.charAt(i));
        }

        for (int i = 1; i < nRows; i++)
            buff[0].append(buff[i]);
        return buff[0].toString();
    }
}

class Solution3 {
    public String convert(String s, int nRows) {
        if (nRows == 1 || s.isEmpty())
            return s;

        char[] result = new char[s.length()];
        for (int i = 0, p = 0; i < nRows; i++) {
            for (int j = 0, index = i; index < s.length(); j++, index = (2 * nRows - 2) * j + i) {
                result[p++] = s.charAt(index);
                if (i == 0 || i == nRows - 1)
                    continue;

                if (index + (nRows - i - 1) * 2 < s.length())
                    result[p++] = s.charAt(index + (nRows - i - 1) * 2);
            }
        }
        return new String(result);
    }
}

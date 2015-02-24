public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int keyMap[] = new int[1 << 20];
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String key = s.substring(i, i + 10);
            if (keyMap[toInt(key)]++ == 1)
                result.add(key);
        }
        return result;
    }

    private int toInt(String segment) {
        int result = 0;
        for (int i = 0; i < segment.length(); i++) {
            char ch = segment.charAt(i);
            result = result * 4 + (ch == 'A' ? 0 : (ch == 'C' ? 1 : (ch == 'G' ? 2 : 3)));
        }
        return result;
    }
}
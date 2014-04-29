public class Solution {
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();
        doRestore(s, path, result, 0);
        return result;
    }

    private void doRestore(String s, ArrayList<String> path, ArrayList<String> result, int start) {
        if (path.size() == 4) {
            if (start == s.length()) {
                StringBuilder ip = new StringBuilder("");
                for (String seg : path)
                    ip.append("." + seg);
                result.add(ip.substring(1));
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String seg = s.substring(start, i + 1);
            if ((seg.charAt(0) == '0' && seg.length() > 1) || Integer.valueOf(seg) > 255)
                break;

            path.add(s.substring(start, i + 1));
            doRestore(s, path, result, i + 1);
            path.remove(path.size() - 1);
        }
    }
}

class Solution2 {
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i <= 3 && i < s.length(); i++) {
            String first = s.substring(0, i + 1);
            if (isInvalid(first))
                break;
            for (int j = i + 1; j <= i + 3 && j < s.length(); j++) {
                String second = s.substring(i + 1, j + 1);
                if (isInvalid(second))
                    break;
                for (int k = j + 1; k <= j + 3 && k < s.length(); k++) {
                    String third = s.substring(j + 1, k + 1);
                    if (isInvalid(third))
                        break;

                    if (k + 1 >= s.length())
                        break;
                    String fourth = s.substring(k + 1);
                    if (fourth.length() <= 3 && !isInvalid(fourth))
                        result.add(first + '.' + second + '.' + third + '.' + fourth);
                }
            }
        }

        return result;
    }

    private boolean isInvalid(String s) {
        return (s.length() > 1 && s.charAt(0) == '0') || Integer.parseInt(s) > 255;
    }
}

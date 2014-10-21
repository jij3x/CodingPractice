public class Solution {
    public String simplifyPath(String path) {
        LinkedList<StringBuilder> buffer = new LinkedList<StringBuilder>();
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '/')
                continue;

            StringBuilder dir = new StringBuilder();
            while (i < path.length() && path.charAt(i) != '/')
                dir.append(path.charAt(i++));

            if (!dir.toString().equals(".") && !dir.toString().equals(".."))
                buffer.add(dir);
            else if (dir.toString().equals("..") && !buffer.isEmpty())
                buffer.removeLast();
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder s : buffer)
            result.append('/').append(s);
        return result.length() == 0 ? "/" : result.toString();
    }
}

class Solution2 {
    public String simplifyPath(String path) {
        LinkedList<String> buffer = new LinkedList<String>();
        String[] dirs = path.trim().split("/");
        for (String d : dirs) {
            if (!d.isEmpty() && !d.equals("..") && !d.equals("."))
                buffer.add(d);
            else if (d.equals("..") && !buffer.isEmpty())
                buffer.removeLast();
        }

        StringBuilder result = new StringBuilder("");
        for (String s : buffer)
            result.append('/').append(s);
        return result.length() == 0 ? "/" : result.toString();
    }
}

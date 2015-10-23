class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            for (char c : str.toCharArray())
                sb.append(c == '/' ? "//" : c);
            sb.append("/.");
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> rst = new ArrayList<String>();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                i++;
                word.append('/');
            } else if (s.charAt(i) == '/') {
                rst.add(word.toString());
                word.setLength(0);
                i++;
            } else {
                word.append(s.charAt(i));
            }
        }
        return rst;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));

public class Solution {
    public int compareVersion(String version1, String version2) {
        ArrayList<Integer> v1 = convertVersion(version1), v2 = convertVersion(version2);
        for (int i = 0; i < Math.min(v1.size(), v2.size()); i++) {
            if (v1.get(i) > v2.get(i))
                return 1;
            if (v1.get(i) < v2.get(i))
                return -1;
        }
        return v1.size() > v2.size() ? 1 : (v1.size() == v2.size() ? 0 : -1);
    }

    private ArrayList<Integer> convertVersion(String version) {
        version += ".";
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0, v = 0; i < version.length(); i++) {
            if (version.charAt(i) == '.') {
                result.add(v);
                v = 0;
            } else {
                v = v * 10 + version.charAt(i) - '0';
            }
        }

        for (int i = result.size() - 1; i >= 0; i--) {
            if (result.get(i) != 0)
                break;
            result.remove(i);
        }
        return result;
    }
}
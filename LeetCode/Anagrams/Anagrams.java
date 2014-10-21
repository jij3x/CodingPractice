public class Solution {
    public ArrayList<String> anagrams(String[] strs) {
        HashMap<String, ArrayList<String>> wordMap = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            ArrayList<String> anagrams = wordMap.containsKey(key) ? wordMap.get(key) : new ArrayList<String>();
            anagrams.add(strs[i]);
            wordMap.put(key, anagrams);
        }

        ArrayList<String> result = new ArrayList<String>();
        for (ArrayList<String> anagrams : wordMap.values()) {
            if (anagrams.size() > 1)
                result.addAll(anagrams);
        }
        return result;
    }
}

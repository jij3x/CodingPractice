public class ValidWordAbbr {
    private HashMap<String, HashSet<String>> memo;
    
    public ValidWordAbbr(String[] dictionary) {
        memo = new HashMap<String, HashSet<String>>();
        for (String word : dictionary) {
            String abbr = word.substring(0, 1) + (word.length() - 1) + word.charAt(word.length() - 1);
            if (!memo.containsKey(abbr))
                memo.put(abbr, new HashSet<String>());
            memo.get(abbr).add(word);
        }
    }

    public boolean isUnique(String word) {
        String abbr = word.substring(0, 1) + (word.length() - 1) + word.charAt(word.length() - 1);
        return !memo.containsKey(abbr) || (memo.get(abbr).contains(word) && memo.get(abbr).size() == 1);
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
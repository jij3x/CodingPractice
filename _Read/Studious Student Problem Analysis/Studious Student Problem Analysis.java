class WordComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return (s1 + s2).compareTo(s2 + s1);
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = Integer.parseInt(in.nextLine());
        for (int i = 0; i < m; i++) {
            LinkedList<String> words = new LinkedList<String>(Arrays.asList(in.nextLine().split("\\s+")));
            words.remove(0);
            Collections.sort(words, new WordComparator());

            StringBuilder builder = new StringBuilder();
            for (String w : words)
                builder.append(w);
            System.out.println(builder.toString());
        }
        in.close();
    }
}

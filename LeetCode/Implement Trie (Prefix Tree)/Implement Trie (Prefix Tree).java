class TrieNode {
    // Initialize your data structure here.
    public TrieNode[] children;
    public boolean stop;

    public TrieNode() {
        children = new TrieNode[128];
        stop = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        int i = 0;
        for (; i < word.length(); i++) {
            if (node.children[word.charAt(i)] == null)
                break;
            node = node.children[word.charAt(i)];
        }
        while (i < word.length()) {
            node.children[word.charAt(i)] = new TrieNode();
            node = node.children[word.charAt(i++)];
        }
        node.stop = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c] != null)
                node = node.children[c];
            else
                return false;
        }
        return node.stop;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c] != null)
                node = node.children[c];
            else
                return false;
        }
        return true;
    }
}

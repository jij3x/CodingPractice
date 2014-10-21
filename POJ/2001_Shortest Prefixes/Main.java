/*
 * Trie
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class TrieNode {
    int count;
    TrieNode[] next;

    TrieNode() {
        count = 1;
        next = new TrieNode[26];
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        TrieNode root = new TrieNode();

        Scanner scn = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<String>();
        while (scn.hasNext()) {
            String word = scn.nextLine();
            insert(word, root);
            words.add(word);
        }
        scn.close();

        for (String word : words) {
            printPrefix(word, root);
        }
    }

    private static void insert(String s, TrieNode root) {
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (root.next[idx] != null) {
                root.next[idx].count++;
            } else {
                root.next[idx] = new TrieNode();
            }
            root = root.next[idx];
        }
    }

    private static void printPrefix(String s, TrieNode root) {
        System.out.format("%s ", s);
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            System.out.print(s.charAt(i));
            root = root.next[idx];
            if (root.count == 1) {
                System.out.println();
                return;
            }
        }
        System.out.println();
    }
}

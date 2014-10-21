/*
 * Huffman Coding
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        while (!line.equals("END")) {
            huffmanCoding(line);
            line = in.readLine();
        }
    }

    class Node {
        char ch;
        int occurrence;
        Node left, right;

        Node(char ch, int occurrence) {
            this.ch = ch;
            this.occurrence = occurrence;
        }
    }

    private static void huffmanCoding(String s) {
        HashMap<Character, Integer> counter = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), (counter.containsKey(s.charAt(i)) ? counter.get(s.charAt(i)) : 0) + 1);
        }

        PriorityQueue<Node> que = new PriorityQueue<Node>(10, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.occurrence - n2.occurrence;
            }
        });

        for (Character ch : counter.keySet())
            que.add(new Main().new Node(ch, counter.get(ch)));

        while (que.size() > 1) {
            Node n = new Main().new Node('*', 0);
            n.left = que.poll();
            n.right = que.poll();
            n.occurrence = n.left.occurrence + n.right.occurrence;
            que.add(n);
        }

        HashMap<Character, Integer> codeMap = new HashMap<Character, Integer>();
        calculate(que.poll(), 0, codeMap);

        int compressed = 0;
        for (int i = 0; i < s.length(); i++) {
            compressed += codeMap.get(s.charAt(i));
        }

        System.out.format("%d %d %.1f%n", s.length() * 8, compressed, (double) s.length() * 8 / compressed);
    }

    private static void calculate(Node root, int level, HashMap<Character, Integer> codeMap) {
        if (root.left == null && root.right == null) {
            codeMap.put(root.ch, level == 0 ? 1 : level);
        } else {
            calculate(root.left, level + 1, codeMap);
            calculate(root.right, level + 1, codeMap);
        }
    }
}

class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        while (!line.equals("END")) {
            huffmanCoding(line);
            line = in.readLine();
        }
    }

    private static void huffmanCoding(String s) {
        int[] occurrences = new int[256];
        for (int i = 0; i < s.length(); i++)
            occurrences[s.charAt(i)]++;

        PriorityQueue<Integer> que = new PriorityQueue<Integer>();
        for (int i : occurrences) {
            if (i > 0)
                que.add(i);
        }

        int compressed = 0;
        while (que.size() > 1) {
            int sum = que.poll() + que.poll();
            compressed += sum;
            que.add(sum);
        }
        compressed = compressed == 0 ? s.length() : compressed;

        System.out.format("%d %d %.1f%n", s.length() * 8, compressed, (double) s.length() * 8 / compressed);
    }
}

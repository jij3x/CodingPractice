import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class BaryTreeNode {
    BaryTreeNode left, right;
}

class NaryTreeNode {
    ArrayList<NaryTreeNode> children;

    NaryTreeNode() {
        children = new ArrayList<NaryTreeNode>();
    }
}

public class Main {
    private static String serialize(NaryTreeNode root) {
        StringBuilder result = new StringBuilder();
        doSerialize(root, result);
        result.setLength(result.length() - 1);
        return result.toString();
    }

    private static void doSerialize(NaryTreeNode root, StringBuilder result) {
        for (int i = 0; i < root.children.size(); i++) {
            result.append("d");
            doSerialize(root.children.get(i), result);
        }
        result.append("u");
    }

    private static int p;

    private static NaryTreeNode deserialize(String s) {
        NaryTreeNode root = new NaryTreeNode();
        p = 0;
        doDeserialize(s, root);
        return root;
    }

    private static void doDeserialize(String s, NaryTreeNode root) {
        while (p < s.length() && s.charAt(p++) == 'd') {
            NaryTreeNode child = new NaryTreeNode();
            root.children.add(child);
            doDeserialize(s, child);
        }
        return;
    }

    private static BaryTreeNode grafting(NaryTreeNode root) {
        if (root == null)
            return null;

        ArrayList<BaryTreeNode> childList = new ArrayList<BaryTreeNode>();
        BaryTreeNode binaryNode = new BaryTreeNode();
        for (int i = 0; i < root.children.size(); i++) {
            childList.add(grafting(root.children.get(i)));
            if (i > 0) {
                childList.get(i - 1).right = childList.get(i);
            }
        }

        binaryNode.left = childList.isEmpty() ? null : childList.get(0);
        return binaryNode;
    }

    private static NaryTreeNode unGrafting(BaryTreeNode root) {
        if (root == null)
            return null;

        NaryTreeNode naryTreeNode = new NaryTreeNode();
        ArrayList<NaryTreeNode> childList = new ArrayList<NaryTreeNode>();
        BaryTreeNode child = root.left;
        while (child != null) {
            childList.add(unGrafting(child));
            child = child.right;
        }

        naryTreeNode.children = childList;
        return naryTreeNode;
    }

    private static int baryTreeHeight(BaryTreeNode root) {
        if (root == null)
            return -1;

        return Math.max(baryTreeHeight(root.left), baryTreeHeight(root.right)) + 1;
    }

    private static int naryTreeHeight(NaryTreeNode root) {
        if (root == null)
            return 0;

        Queue<NaryTreeNode> workingQue = new LinkedList<NaryTreeNode>();
        workingQue.add(root);
        int level = -1, currLevel = 1, nextLevel = 0;
        while (currLevel > 0) {
            NaryTreeNode node = workingQue.poll();
            currLevel--;
            for (int i = 0; i < node.children.size(); i++) {
                workingQue.add(node.children.get(i));
                nextLevel++;
            }

            if (currLevel == 0) {
                level++;
                currLevel = nextLevel;
                nextLevel = 0;
            }
        }
        return level;
    }

    /*
     * This solution implements serialization/deserialization, grafting.
     * It's slow and uses more memory, but good for referencing.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        for (int i = 1; !line.equals("#"); line = in.readLine(), i++) {
            NaryTreeNode root = deserialize(line);
            System.out.format("Tree %d: %d => %d\n", i, naryTreeHeight(root), baryTreeHeight(grafting(root)));
        }
    }
}

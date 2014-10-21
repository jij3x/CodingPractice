/*
 * Two heaps (min-heap and max-heap)
 */

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class BlackBox {
    Comparator<Integer> minComparator;
    Comparator<Integer> maxComparator;
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    BlackBox() {
        minComparator = new MinComparator();
        maxComparator = new MaxComparator();
        minHeap = new PriorityQueue<Integer>(30001, minComparator);
        maxHeap = new PriorityQueue<Integer>(30001, maxComparator);
    }

    void add(int a) {
        if (maxHeap.size() == 0 || a >= maxHeap.peek()) {
            minHeap.add(a);
        } else {
            maxHeap.add(a);
            minHeap.add(maxHeap.poll());
        }
    }

    int get() {
        maxHeap.add(minHeap.poll());
        return maxHeap.peek();
    }
}

class MinComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
        return a == b ? 0 : (a > b ? 1 : -1);
    }
}

class MaxComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
        return a == b ? 0 : (a > b ? -1 : 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        ArrayList<Integer> addList = new ArrayList<Integer>();
        for (int i = 0; i < m; i++)
            addList.add(scanner.nextInt());
        ArrayList<Integer> getList = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
            getList.add(scanner.nextInt());
        scanner.close();

        int addIndex = 0, getIndex = 0;
        BlackBox bb = new BlackBox();
        while (addIndex < addList.size() || getIndex < getList.size()) {
            if (addIndex < addList.size()) {
                bb.add(addList.get(addIndex++));
            }

            while (getIndex < getList.size() && getList.get(getIndex) == addIndex) {
                System.out.println(bb.get());
                getIndex++;
            }
        }
    }
}

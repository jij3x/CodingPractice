import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		int cases = scanner.nextInt();
		while (cases-- > 0) {
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < n; i++) {
				int m = scanner.nextInt();
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int j = 0; j < m; j++) {
					list.add(scanner.nextInt());
				}
				lists.add(list);
			}
			System.out.println(qualifiedKth(lists, k));
		}
		scanner.close();
	}

	private static int qualifiedKth(ArrayList<ArrayList<Integer>> input, int k) {
		for (int i = 0; i < input.size(); i++) {
			Collections.sort(input.get(i));
		}

		int count = 0;
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < input.get(i).size(); j++) {
				int target = input.get(i).get(j);
				int lessThanCount = 0;
				int moreThanCount = 0;
				for (int x = 0; x < input.size(); x++) {
					if (x == i)
						continue;

					ArrayList<Integer> list = input.get(x);
					if (list.get(0) < target)
						lessThanCount++;
					if (list.get(list.size() - 1) > target)
						moreThanCount++;
				}

				if (lessThanCount >= k - 1 && moreThanCount >= input.size() - k)
					count++;
			}
		}

		return count;
	}
}

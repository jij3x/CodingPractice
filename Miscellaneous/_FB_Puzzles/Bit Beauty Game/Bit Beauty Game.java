import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		ArrayList<Integer> inputs = new ArrayList<Integer>();
		int cases = scanner.nextInt();
		while (cases-- > 0) {
			inputs.add(scanner.nextInt());
		}
		scanner.close();

		for (int i = 0; i < inputs.size(); i++)
			System.out.println(solver(inputs.get(i)) ? "First Player" : "Second Player");
	}

	private static boolean solver(int num) {
		/*
		 * Example:
		 *     101010 (42) will eventually become 000111 after picking in turn. Total routes
		 *     are 6 (3+2+1), that is, all possible ways 1s move to their final positions. 
		 */
		int counts = 0;
		int posCounts = 0;
		int i = 1;

		while (num > 0) {
			if ((num & 1) == 1) {
				counts++;
				posCounts += i;
			}
			num >>= 1;
			i++;
		}

		counts = (1 + counts) * counts / 2;
		if (((posCounts - counts) & 1) == 1)
			return true;

		return false;
	}
}

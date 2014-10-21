import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		String branch = buf.readLine();

		for (int len = branch.length() / 2; len > 1; len--) {
			for (int i = 0; i <= branch.length() - len * 2; i++) {
				for (int j = i + len; j <= branch.length() - len; j++) {
					int sum = 0, wSum1 = 0, wSum2 = 0, wSum3 = 0, wSum4 = 0;
					for (int p = 0; p < len; p++) {
						sum += branch.charAt(i + p) - '0';
						sum += branch.charAt(j + p) - '0';

						wSum1 += (branch.charAt(i + p) - '0') * (p + 1) + (branch.charAt(j + p) - '0') * (p + 1 + len);
						wSum2 += (branch.charAt(i + p) - '0') * (len - p) + (branch.charAt(j + p) - '0')
								* (p + 1 + len);
						wSum3 += (branch.charAt(i + p) - '0') * (p + 1) + (branch.charAt(j + p) - '0') * (len * 2 - p);
						wSum4 += (branch.charAt(i + p) - '0') * (len - p) + (branch.charAt(j + p) - '0')
								* (len * 2 - p);
					}

					if (((wSum1 * 2) / sum == len * 2 + 1 && (wSum1 * 2) % sum == 0)
							|| ((wSum2 * 2) / sum == len * 2 + 1 && (wSum2 * 2) % sum == 0)
							|| ((wSum3 * 2) / sum == len * 2 + 1 && (wSum3 * 2) % sum == 0)
							|| ((wSum4 * 2) / sum == len * 2 + 1 && (wSum4 * 2) % sum == 0)) {
						System.out.println(i + " " + j + " " + len);
						return;
					}
				}
			}
		} // end for (len ...)
	}
}
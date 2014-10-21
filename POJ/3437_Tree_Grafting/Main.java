import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int p;

	private static void getHeights(String s, int level, int graftingLevel, int[] heights) {
		heights[0] = Math.max(heights[0], level);
		while (p < s.length() && s.charAt(p++) == 'd') {
			getHeights(s, level + 1, ++graftingLevel, heights);
		}
		heights[1] = Math.max(heights[1], graftingLevel);
	}

	private static void getHeightsIterative(String s, int[] stack, int[] heights) {
		stack[0] = 0;
		int top = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'd') {
				stack[top]++;
				heights[1] = Math.max(heights[1], stack[top]);
				stack[++top] = stack[top - 1];
				heights[0] = Math.max(heights[0], top);
			} else {
				top--;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		for (int i = 1; !line.equals("#"); line = in.readLine(), i++) {
			int[] heights = new int[2];
			p = 0;
			getHeights(line, 0, 0, heights);
			System.out.format("Tree %d: %d => %d\n", i, heights[0], heights[1]);
		}
	}
}

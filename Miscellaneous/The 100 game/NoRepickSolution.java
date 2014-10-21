public class Solution {
    public int pick(boolean[] visited, int target) {
        int max = getMaxUnvisited(visited);
        if (max >= target)
            return max;

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                boolean allWin = true;
                visited[i] = true;

                for (int j = 0; j < visited.length; j++) {
                    if (pick(visited, target - i - 1) > 0) {
                        allWin = false;
                        break;
                    }
                }

                visited[i] = false;
                if (allWin)
                    return i + 1;
            }
        }
        return 0;
    }

    private int getMaxUnvisited(boolean[] visited) {
        int max = visited.length;
        while (max > 0) {
            if (!visited[--max])
                break;
        }
        return ++max;
    }
}
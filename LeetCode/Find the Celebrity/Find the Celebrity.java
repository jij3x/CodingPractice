/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int mostLike = 0;
        for (int i = 1; i < n; i++) {
            if (knows(mostLike, i))
                mostLike = i;
        }
        
        for (int i = 0; i < n; i++) {
            if (i != mostLike && (!knows(i, mostLike) || knows(mostLike, i)))
                return -1;
        }
        return mostLike;
    }
}
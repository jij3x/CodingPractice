public class Solution {
    private class myComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            long l1 = Long.parseLong(o1.toString() + o2.toString());
            long l2 = Long.parseLong(o2.toString() + o1.toString());
            return l2 - l1 > 0 ? 1 : (l2 - l1 < 0 ? -1 : 0);
        }
    }

    public String largestNumber(int[] num) {
        boolean allZero = true;
        for (int n : num) {
            if (n != 0)
                allZero = false;
        }
        if (allZero)
            return "0";

        ArrayList<Integer> numList = new ArrayList<Integer>();
        for (int n : num)
            numList.add(n);
        Collections.sort(numList, new myComparator());

        StringBuilder sb = new StringBuilder();
        for (Integer n : numList)
            sb.append(n.toString());
        return sb.toString();
    }
}
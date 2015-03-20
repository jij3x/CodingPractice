public class TwoSum {
    private HashMap<Integer, Integer> counter;

    public TwoSum() {
        this.counter = new HashMap<Integer, Integer>();
    }

    public void add(int number) {
        Integer c = counter.get(number);
        counter.put(number, c == null ? 1 : c + 1);
    }

    public boolean find(int value) {
        for (int n : counter.keySet()) {
            if (counter.containsKey(value - n) && (value - n != n || counter.get(value - n) > 1))
                return true;
        }
        return false;
    }
}
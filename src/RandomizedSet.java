import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

// A data structure support insert(), remove() and getRandom all in O(1)
class RandomizedSet {
    private List<Integer> values;
    private Random r;
    private HashMap<Integer, Integer> map;

    /** Initialize data structure here. */
    RandomizedSet() {
        values = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
        r = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    boolean insert(int val) {
        if (!map.containsKey(val)) {
            values.add(val);
            map.put(val, values.size()-1);
            return true;
        } else
            return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    boolean remove(int val) {
        if (map.containsKey(val)) {
            if (map.get(val) != values.size()-1) {
                int lastVal = values.get(values.size()-1);
                values.set(map.get(val), lastVal); //get the index of val from map, swap val with last value in the list
                map.put(lastVal, map.get(val)); //update the index of lastVal because it is moved
            }
            values.remove(values.size()-1);
            map.remove(val); //remove val from map
            return true;
        } else
            return false;
    }

    /** Get a random element from the set. */
    int getRandom() {
        int idx = r.nextInt(values.size());
        return values.get(idx);
    }
}


package cache;

import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 上午11:12 2021/4/20
 */
public class LRUCache2 {
    private int capacity;
    private Map<Integer, Integer> cache;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        this.cache = new java.util.LinkedHashMap<Integer, Integer> (capacity, 0.75f, true) {
            // 定义put后的移除规则，大于容量就删除eldest
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else
            return -1;
    }

    public void set(int key, int value) {
        cache.put(key, value);
    }
}

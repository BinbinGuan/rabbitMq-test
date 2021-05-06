package cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkHashMap提供LRU的实现，只要new的时候将构造器的参数accessOrder设置为true，默认为false。
 * 可以看看LinkHashMap的get和put源码。
 * 当accessOrder设置为true时，get时调用了 afterNodeAccess()方法，将最近访问过的节点放置链表最后。
 * put时先调用了 afterNodeAccess()方法，然后调用了afterNodeInsertion()方法，这个方法主要作用就是当size大于capacity时，将最久未使用的结点移除。
 * 重写removeEldestEntry（）方法，就是为了让LinkHashMap知道当size大于多少capacity时，开始删除结点。
 * 最后附上java8 LinkHashMap的afterNodeAccess()方法与afterNodeInsertion()方法源码。
 * <p>
 * 作者：wky233
 * 链接：https://leetcode-cn.com/problems/lru-cache-lcci/solution/linkhashmapzhong-xie-removeeldestentryfang-fa-ku
 *
 * @author: GuanBin
 * @date: Created in 上午11:26 2021/4/20
 */
public class LRUCache3 {

    class LRU<K, V> extends LinkedHashMap<K, V> {

        private int capacity;

        public LRU(int capacity) {
            super(capacity, (float) 0.75, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }


    LRU<Integer, Integer> lru;

    public LRUCache3(int capacity) {
        lru = new LRU<>(capacity);
    }

    public int get(int key) {
        return lru.get(key) == null ? -1 : lru.get(key);
    }

    public void put(int key, int value) {
        lru.put(key, value);
    }
}

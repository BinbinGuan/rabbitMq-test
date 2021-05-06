package cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 上午10:11 2021/4/20
 */
public class LRUCache {

    class Node {
        Node pre;
        Node next;
        Integer key;
        Integer val;

        /**
         * @param k 节点key
         * @param v 节点值
         */
        Node(Integer k, Integer v) {
            key = k;
            val = v;
        }

        Map<Integer, Node> map = new HashMap<Integer, Node>();
        Node head;
        Node tail;
        int cap;

        public Node(int capacity) {
            this.cap = capacity;
            head = new Node(null, null);
            tail = new Node(null, null);
            head.next = tail;
            tail.pre = head;
        }

        // a->b->c
        // a<-b<-c
        public int get(int key) {
            Node n = map.get(key);
            if (n != null) {
                n.pre.next = n.next;
                n.next.pre = n.pre;
                appendTail(n);
                return n.val;
            }
            return -1;
        }

        public void set(int key, int value) {
            Node node = map.get(key);

            // existed
            if (node != null) {
                node.val = value;
                map.put(key, node);
                node.pre.next = node.next;
                node.next.pre = node.pre;
                appendTail(node);
                return;
            }
            // a->b->c
            if (map.size() == cap) {
                Node tmp = head.next;
                head.next = head.next.next;
                head.next.pre = head;
                map.remove(tmp.key);
            }
            node = new Node(key, value);
            // youngest node append taill
            appendTail(node);
            map.put(key, node);
        }

        private void appendTail(Node n) {
            n.next = tail;
            n.pre = tail.pre;
            tail.pre.next = n;
            tail.pre = n;
        }
    }
}

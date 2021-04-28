package data;

import org.apache.commons.lang3.ArrayUtils;

public class Queue<E> {
    private Object[] data = null;
    private int maxSize;
    private int front;
    private int rear;

    public Queue() {
        this(10);
    }

    public Queue(int initialSize) {
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new Object[initialSize];
            front = rear = 0;
        } else {
            throw new RuntimeException("初始化大小不能小于0：" + initialSize);
        }
    }

    public boolean add(E e) {
        if (rear == maxSize) {
            throw new RuntimeException("队列已满，无法插入元素：" + e);
        } else {
            data[rear++] = e;
            return true;
        }
    }

    public E poll() {
        if (ArrayUtils.isEmpty(data)) {
            throw new RuntimeException("空队列异常");
        } else {
            E value = (E) data[front];
            data[front++] = null;
            return value;
        }
    }

    public E peek() {
        if (ArrayUtils.isEmpty(data)) {
            throw new RuntimeException("空队列异常");
        } else {
            return (E) data[front];
        }
    }
}

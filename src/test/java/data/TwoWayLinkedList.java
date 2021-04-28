package data;

public class TwoWayLinkedList {
    private Node head;
    private Node tail;
    private int length;

    private class Node {
        private Object data;
        private Node next;
        private Node prev;

        public Node(Object data) {
            this.data = data;
        }
    }

    private TwoWayLinkedList() {
        length = 0;
        head = null;
        tail = null;
    }

    public void addHead(Object value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
            length++;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            length++;
        }

    }

    public void addTail(Object value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
            length++;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            length++;
        }

    }

    public Node deleteHead() {
        Node temp = head;
        if (length != 0) {
           head= head.next;
           head.prev=null;
           length--;
           return temp;
        } else {
           return null;
        }
    }

    public Node deleteTail() {
        Node temp =tail;
        if (length != 0) {
            tail= tail.prev;
            tail.next=null;
            length--;
            return temp;
        } else {
            return null;
        }
    }
}

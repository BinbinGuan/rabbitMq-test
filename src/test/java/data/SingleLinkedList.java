package data;


public class SingleLinkedList {
    private int length;
    private Node head;

    public SingleLinkedList() {
        length = 0;
        head = null;
    }

    private class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }


    public Object addHead(Object obj) {
        Node newNode = new Node(obj);
        if (length == 0) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
        return obj;
    }

    public boolean delete(Object value) {
        if (length == 0) {
            return false;
        }

        Node current = this.head;
        Node previous = this.head;
        while (current.data != value) {
            if (current.next == null) {
                return false;
            } else {
                previous = current;
                current = current.next;
            }
        }
        if (current == head) {
            head = current.next;
            length--;
        } else {
            previous.next = current.next;
            length--;
        }
        return true;
    }

    public Node find(Object obj) {
        Node current = this.head;
        int tempSize = length;
        while (tempSize > 0) {
            if (obj.equals(current.data)) {
                return current;
            } else {
                current = current.next;
            }
            tempSize--;
        }
        return null;
    }
}

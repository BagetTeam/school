package data_structures;

import data_structures.interfaces.SList;

public class SSinglyLinkedList<E> implements SList<E> {
    private class SSLLNode {
        private E data;
        private SSLLNode next;

        SSLLNode(E data) {
            this(data, null);
        }

        SSLLNode(E data, SSLLNode next) {
            this.data = data;
            this.next = next;
        }
    }

    private SSLLNode head;
    private SSLLNode tail;
    private int size;

    public SSinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(E e) {
        // TODO: add element to end of list
        if (this.head == null) {
            this.head = new SSLLNode(e);
            this.tail = this.head;
        } else {
            this.tail.next = new SSLLNode(e);
            this.tail = this.tail.next;
        }
        size++;
    }

    @Override
    public E get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        SSLLNode pointer = getNode(index);
        return pointer.data;
    }

    @Override
    public E remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        SSLLNode ret;
        if (index == 0) {
            ret = head;
            head = head.next;
            if (ret == tail)
                tail = null;
        } else if (index == size - 1) {
            ret = tail;
            SSLLNode prev = getNode(index - 1);
            tail = prev;
            tail.next = null;
        } else {
            SSLLNode prev = getNode(index - 1);
            ret = prev.next;
            prev.next = prev.next.next;
        }
        size--;
        return ret.data;
    }

    @Override
    public int size() {
        return size;
    }

    private SSLLNode getNode(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        // return the i-th node from head
        // TODO: implement (refer to lecture slides)
        SSLLNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public String toString() {
        String msg = "size = " + size + "\n";
        SSLLNode ptr = head;
        for (int i = 0; i < size; i++) {
            msg = msg + ptr.data + ",";
            ptr = ptr.next;
        }
        return msg;
    }
}

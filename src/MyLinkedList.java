public class MyLinkedList<E> {
    private int size;
    private Node first;
    private Node last;
    private class Node {
        E item;
        Node next;
        Node prev;
        Node(E element) {
            this.item = element;
            this.next = null;
            this.prev = null;
        }
        Node(Node prev, E element, Node next ) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
    MyLinkedList() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }
        private Node getNode (int index) {
            if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            Node node = first;
            for (int i = 0; i < index; i++){
                node = node.next;
            }
            return node;
        }
        public E get(int index) {
        Node node = getNode(index);
        return node.item;
        }
        public E getFirst() {
        if (first == null) throw new NullPointerException();
        return first.item;
        }
        public void addFirst (E element) {
            if (first == null) {
                Node node = new Node(element);
                first = node;
                last = node;
            } else {
                Node node = new Node(null, element, first);
                first.prev = node;
                first = node;
            }
            size++;
        }
        public  void addLast(E element) {
            if (last == null) {
                Node node = new Node(element);
                first = node;
                last = node;
            } else {
               Node node = new Node(last,element, null);
                   last.next = node;
                   last = node;
               }
            size++;
            }
            public  void add (int index, E element) {
        if (index == 0) {
            addFirst(element);
        }
        Node node = getNode(index);
        Node newNode = new Node(node.prev,element,node.next);
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
    }
    public int getSize() {
        return size;
    }
    public boolean remove(int index) {
        if (size == 1) {
            first = null;
            last = null;
        } else if (index == 0) {
            first.next.prev = null;
            first = first.next;
        } else  if (index == size-1) {
            last.prev.next = null;
            last = last.prev;
        } else {
        Node node = getNode(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        }
        size--;
        return true;
    }

}



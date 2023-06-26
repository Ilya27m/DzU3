public class MyBinaryTree<E> {
    private int size = 0;
    private Node root = null;

    private class Node {
        public E element;
        public Node left;
        private Node right;

        public Node(E element) {
            this.element = element;
            left = null;
            right = null;
        }
    }

    public void add(E element) {
        if (root == null) {
            root = new Node(element);
            size++;
            return;
        }
        putHelper(root, element);

    }

    private void putHelper(Node node, E element) {
        Comparable<E> k = (Comparable<E>) element;
        int cmp = k.compareTo(node.element);
        if (cmp <= 0) {
            if (node.left == null) {
                node.left = new Node(element);
                size++;
                return;
            }
            putHelper(node.left, element);
        }
        if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(element);
                size++;
                return;
            }
            putHelper(node.right, element);
        }
        return;
    }

    public boolean contains(Object element) {
        Node node = findNode(element);
        if (node == null) return false;
        return true;
    }

    private Node findNode(Object target) {
        Comparable<E> k = (Comparable<E>) target;
        Node node = root;
        while (node != null) {
            int cmp = k.compareTo(node.element);
            if (cmp < 0) {
                node = node.left;
            }
            if (cmp > 0) {
                node = node.right;
            }
            if (cmp == 0) return node;
        }
        return null;
    }

    private Node findParent(Object target) {
        Comparable<E> k = (Comparable<E>) target;
        Node node = root;
        Node parent = root;
        while (node != null) {
            int cmp = k.compareTo(node.element);
            if (cmp < 0) {
                parent = node;
                node = node.left;
            }
            if (cmp > 0) {
                parent = node;
                node = node.right;
            }
            if (cmp == 0) return parent;
        }
        return null;
    }

    public E remove(Object element) {
        Node child = findNode(element);
        if (child == null) return null;
        if (size == 1) {
            root = null;
            size--;
            return child.element;

        }
        Node parent = findParent(element);
        if (child.left != null && child.right != null) {
            Node preemnik = findPreemnik(child.right);
            Node preParent = findParent(preemnik.element);
            removeHelper (preemnik, preParent);
            child.element = preemnik.element;
            E oldValue = child.element;
            child.element = preemnik.element;
            return oldValue;
        } else if (child == root) {
            if (child.left != root) root = child.left;
            if (child.right != root) root = child.right;
            size--;
            return child.element;
        } else {
            return  removeHelper(child,parent);
        }
    } private E removeHelper(Node child, Node parent) {
        if (child.left == null && child.right == null) {
            if (parent.right == child) parent.right = null;
            if (parent.left == child) parent.left = null;
            size--;
            return child.element;
        }
        if (child.left == null) {
            if (parent.right == child) parent.right = child.left;
            if (parent.left == child) parent.left = child.right;
            size--;
            return child.element;
        }
        if (child.right == null) {
            if (parent.right == child) parent.right = child.left;
            if (parent.left == child) parent.left = child.left;
            size--;
            return child.element;
        }
        return null;
    }
    private Node findPreemnik(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    public void printTree() {
        if (root == null) {
            System.out.println("Tree is empty");
        }
        System.out.println("Root is" + root.element);
        LER(root);
    }
    private void LER(Node node) {
        if (node.left != null) LER(node.left);
        System.out.println(node.element);
        if (node.right != null) LER(node.right);
    }
}

public class MyTreeMap<K,V> {
    private int size;
    private Node root = null;
    private class Node {
        public K key;
        public V value;
        public  Node left=null;
        private Node right=null;
        public  Node (K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private V put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return null;
        }
        return putHelper(root,key,value);

    }
    private  V putHelper (Node node, K key, V value) {
        Comparable<? super K> k = (Comparable<? super K>) key;
        int cmp = k.compareTo(node.key);
        if (cmp <0) {
            if (node.left == null) {
                node.left = new Node(key,value);
                size++;
                return null;
            }
            return putHelper(node.left,key,value);
        }
        if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(key,value);
                size++;
                return null;
            }
            return putHelper(node.right,key,value);
        }
            V oldValue = node.value;
            node.value = value;
            return  oldValue;
        }

        public V get (Object key) {
        Node node = findNode(key);
        if (node == null) return null;
        return node.value;
        }
        private Node findNode (Object target) {
        Comparable<? super K> k = (Comparable<? super K>) target;
        Node node = root;
        while (node != null) {
            int cmp = k.compareTo(node.key);
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
        Comparable<? super K> k = (Comparable<? super K>) target;
        Node node = root;
        Node parent = root;
            while (node != null) {
                int cmp = k.compareTo(node.key);
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
    public V remove(Object key) {
        V oldValue = get(key);
        if (key == root.key) root = delRecursive(key);
        else delRecursive(key);
        return oldValue;
    }
    private Node delRecursive (Object key){
        Node node = findNode(key);
        Node parent = findParent(key);
        if (node.left == null && node.right ==null) {
            if (node == parent.left) parent.left = null;
            if (node == parent.right) parent.right = null;
            size--;
            return parent;
        }
        if (node.right == null) {
            if (node == parent.left) parent.left = node.left;
            if (node == parent.right) parent.right = node.left;
            size--;
            return parent;
        }
        if (node.left == null) {
            if (node == parent.left) parent.left = node.right;
            if (node == parent.right) parent.right = node.right;
            size--;
            return parent;
        }
        Node tempNode = findSmallest(node.right);
        delRecursive(tempNode.key);
        node.key = tempNode.key;
        node.value = tempNode.value;
        return parent;
    }
    private Node findSmallest (Node node) {
        if (node.left == null) return node;
        else {
            return  findSmallest(node.left);
        }
    }
        public void printTree() {
        recPrintTree(root);
        System.out.println("");
    }
    private  void recPrintTree (Node node) {
        if (node.left != null) recPrintTree(node.left);
        System.out.println(node.value);
        if (node.right != null) recPrintTree(node.right);
    }
}

public class Main {
    public static void main(String[] args) {
        MyBinaryTree b = new MyBinaryTree();
        b.add(4);
        b.add(6);
        b.add(7);
        b.add(9);
        b.add(8);
        b.add(1);
        b.add(4);
        b.add(5);
        b.add(3);
        b.add(2);
        b.add(0);
        b.printTree();
        b.remove(4);
        b.remove(4);
        b.remove(5);
        b.remove(6);
        b.remove(3);
        b.printTree();
    }
}
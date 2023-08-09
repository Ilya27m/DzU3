public class Main {
    public static void main(String[] args) {
        MyBinaryTree b = new MyBinaryTree();
        b.add(4);
        b.add(2);
        b.add(3);
        b.add(1);
        b.add(7);
        b.add(6);
        b.add(8);
        b.printTree();
        b.remove(4);  //удаление корня
        b.printTree();
        b.remove(7);
        b.remove(1); //удаление листьев
        b.printTree();

    }
}
package Internship;

/**
 * Klasa Node to podstawowa jednostka implementowanej przeze mnie struktury.
 * Składa się z 3 prywatnych pól:
 * -value -> wartość węzła
 * -leftChild -> możliwy węzeł "dziecko" znajdujący się po lewej stronie
 * -rightChild -> analogicznie
 * jako że pola są prywatne, mają odpowiednie gettery i settery
 */

public class Node {
    private int value;
    private Node leftChild;
    private Node rightChild;

    /**
     * Konstruktor przyjmuje jedynie wartość, mój program zakłada że nie da się utworzyć węzła bez wartości
     */
    public Node(int value) {
        this.value = value;
    }

    /**
     * Metoda print opakowująca wartość węzła w nawiasy.
     */
    public void print() {
        System.out.println("(" + this.value + ")");
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Po użyciu metody na korzeniu, wszystkie węzły zostają wypisane zgodnie z przejściem preorder.
     * W przypadku wywołania metody z podaniem innego niż korzeń węzła, metoda wypisuje jego poddrzewo.
     */
    public static void printTree(Node root) {
        root.print();
        if (root.getLeftChild() != null) {
            printTree(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            printTree(root.getRightChild());
        }
    }

    /**
     * Metoda leafCounter to rozwiązanie podpunktu 1.
     * Rekurencyjnie wylicza liczbę liści w drzewie, i zwraca ją jako int.
     * W przypadku podania przy wywołaniu null, wyrzuca wyjątek EmptyTreeException z podaniem powodu.
     * W przypadku podania innego węzła niż korzeń drzewa, zostanie wyliczona liczba liści poddrzewa.
     */
    public static int leafCounter(Node root) throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("Root passed to leafCounter method is null.");
        }
        int childlessNodesCount = 0;
        boolean isChildless = true;
        if (root.getLeftChild() != null) {
            childlessNodesCount += leafCounter(root.getLeftChild());
            isChildless = false;
        }
        if (root.getRightChild() != null) {
            childlessNodesCount += leafCounter(root.getRightChild());
            isChildless = false;
        }
        if (isChildless) {
            childlessNodesCount++;
        }
        return childlessNodesCount;
    }

    /**
     * Metoda treeHeight to rozwiązanie zadania z podpunktu 2.
     * Rekurencyjnie przechodzi od korzenia do liści po istniejących "dzieciach" i następnie zlicza je,
     * wybierając przy każdym złączeniu wysokość wyższego poddrzewa.
     * W przypadku podania innego węzła niż korzeń przy wywołaniu, metoda policzy wysokość poddrzewa,
     * a w przypadku podania null, wyrzuci wyjątek.
     * Zwraca wysokość drzewa jako int.
     */
    public static int treeHeight(Node root) throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("Root passed to treeHeight method is null.");
        }
        int leftHeight = 0, rightHeight = 0;
        if (root.getLeftChild() != null) {
            leftHeight++;
            leftHeight += treeHeight(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            rightHeight++;
            rightHeight += treeHeight(root.getRightChild());
        }
        return Math.max(leftHeight, rightHeight);
    }

    /**
     * Metoda isEqual to rekurencyjne rozwiązanie zadania z podpunktu 3.
     * Metoda pobiera przy wywołaniu dwa korzenie i sprawdza czy ich drzewa są identyczne, to znaczy czy
     * mają w odpowiadających polach węzły o tych samych wartościach i zwraca true/false.
     * W przypadku podania innego węzła niż korzeń - metoda nie cofnie się do korzenia, gdyż węzły nie zawierają
     * informacji o swoim rodzicu i sprawdzi czy poddrzewa podanych węzłów są identyczne.
     * Wiem, że polecenie sugerowało utworzenie osobnej struktury na całe drzewo i sprawdzanie od nominalnego korzenia,
     * jednak ta wersja implementacji z samymi węzłami wydała mi się bardziej czytelna i optymalna, gdyż nie trzeba
     * dodawać każdego tworzonego węzła do jakiejś struktury, ani nadawać mu od razu rodzica (iteracja po wszystkich
     * węzłach i przypisywanie każdemu rodzica marnowałaby za dużo zasobów).
     * W dużym skrócie -> aby porównać drzewa w całości należy wywołać metodę na korzeniach.
     *
     * W metodzie założyłem że dwa węzły równe null są równe.
     */
    public static boolean isEqual(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        }
        boolean flagLeft = true, flagRight = true;
        if (root1.getValue() != root2.getValue()) {
            return false;
        }

        if ((root1.getLeftChild() != null && root2.getLeftChild() == null) || (root1.getLeftChild() == null && root2.getLeftChild() != null)) {
            return false;
        } else if (root1.getLeftChild() != null && root2.getLeftChild() != null) {
            flagLeft = isEqual(root1.getLeftChild(), root2.getLeftChild());
        }

        if ((root1.getRightChild() != null && root2.getRightChild() == null) || (root1.getRightChild() == null && root2.getRightChild() != null)) {
            return false;
        } else if (root2.getRightChild() != null && root2.getRightChild() != null) {
            flagRight = isEqual(root1.getRightChild(), root2.getRightChild());
        }
        return flagLeft && flagRight;
    }
}

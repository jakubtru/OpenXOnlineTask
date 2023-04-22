package Internship;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Klasa z testami do metod leafCounter (zadanie1), treeHeight (zadanie2) oraz isEqual (zadanie3)
 * Testów nie jest wiele, ale pokrywają większość nietypowych przypadków.
 */
class NodeTest {
    Root getExampleTree() {
        //drzewo z przykładu z pliku PDF - potrzebne w kilku testach
        Root root = new Root(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(5);
        Node node6 = new Node(1);
        Node node7 = new Node(0);
        Node node8 = new Node(2);
        Node node9 = new Node(8);
        Node node10 = new Node(5);
        root.setLeftChild(node2);
        root.setRightChild(node3);
        node2.setLeftChild(node4);
        node2.setRightChild(node5);
        node3.setLeftChild(node6);
        node3.setRightChild(node7);
        node7.setLeftChild(node8);
        node7.setRightChild(node9);
        node9.setRightChild(node10);
        return root;
    }

    @org.junit.jupiter.api.Test
    void leafCounter0() throws EmptyTreeException {
        //jako korzeń podajemy null - następnie wyłapujemy wyjątek
        Root root = null;
        try {
            assertEquals(Node.leafCounter(root), 0);
        } catch (EmptyTreeException ignored) {
        }
    }

    @org.junit.jupiter.api.Test
    void leafCounter1() throws EmptyTreeException {
        //drzewo z przykładu z pliku PDF
        Root root = getExampleTree();
        assertEquals(Node.leafCounter(root), 5);
    }

    @org.junit.jupiter.api.Test
    void leafCounter2() throws EmptyTreeException {
        /*
        struktura generowanego do testu drzewa:
                    (1) - root
               (1)      (2)
                    (1)     (2)
                        (1)     (2)
                             (1)     (2)
                                 (1)     (2)
                                     (1)     (2)
                                         (1)     (2)
                                             (1)     (2)
                                                 (1)     (2)
                                                     (1)     (2)
         ma ono 11 liści
         */
        Node node = new Root(1);
        Node rootCopy = node;
        for (int i = 0; i < 10; i++) {
            node.setLeftChild(new Node(1));
            node.setRightChild(new Node(2));
            node = node.getRightChild();
        }
        assertEquals(Node.leafCounter(rootCopy), 11);
    }

    @org.junit.jupiter.api.Test
    void leafCounter3() throws EmptyTreeException {
        /*
        struktura generowanego do testu drzewa:
                 (1)
             (2)     (3)
           (4) (5)     (6)
           ma ono 3 liście
         */
        Root root = new Root(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        root.setLeftChild(node2);
        root.setRightChild(node3);
        node2.setLeftChild(node4);
        node2.setRightChild(node5);
        node3.setRightChild(node6);
        assertEquals(Node.leafCounter(root), 3);
    }

    @org.junit.jupiter.api.Test
    void treeHeight0() {
        //jako korzeń podajemy null - następnie wyłapujemy wyjątek
        Root root = null;
        try {
            assertEquals(Root.treeHeight(root), 0);
        } catch (EmptyTreeException ignored) {
        }
    }

    @org.junit.jupiter.api.Test
    void treeHeight1() throws EmptyTreeException {
        Root root = getExampleTree();
        assertEquals(Root.treeHeight(root), 4);
    }

    @org.junit.jupiter.api.Test
    void treeHeight2() throws EmptyTreeException {
        /*
        struktura generowanego do testu drzewa:
                    (1) - root
               (1)      (2)
                    (1)     (2)
                        (1)     (2)
                             (1)     (2)
                                 (1)     (2)
                                     (1)     (2)
                                         (1)     (2)
                                             (1)     (2)
                                                 (1)     (2)
                                                     (1)     (2)
         ma ono wysokość (najdłuższą ścieżkę pomiędzy korzeniem i liściem) równą 10
         */
        Node node = new Root(1);
        Node rootCopy = node;
        for (int i = 0; i < 10; i++) {
            node.setLeftChild(new Node(1));
            node.setRightChild(new Node(2));
            node = node.getRightChild();
        }
        assertEquals(Node.treeHeight(rootCopy), 10);
    }

    @org.junit.jupiter.api.Test
    void treeHeight3() throws EmptyTreeException {
        /*
        struktura generowanego do testu drzewa:
                 (1)
             (2)     (3)
           (4) (5)     (6)
           ma ono wysokość 2
         */
        Root root = new Root(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        root.setLeftChild(node2);
        root.setRightChild(node3);
        node2.setLeftChild(node4);
        node2.setRightChild(node5);
        node3.setRightChild(node6);

        assertEquals(Node.treeHeight(root), 2);
    }

    @org.junit.jupiter.api.Test
    void treeHeight4() throws EmptyTreeException {
        Root root = new Root(1);
        assertEquals(Root.treeHeight(root), 0);
    }

    @org.junit.jupiter.api.Test
    void treeHeight5() throws EmptyTreeException {
        Root root = new Root(1);
        Node node = new Node(2);
        root.setLeftChild(node);
        assertEquals(Root.treeHeight(root), 1);
    }


    @org.junit.jupiter.api.Test
    void isEqual0() {
        assertTrue(Node.isEqual(null, null));
    }

    @org.junit.jupiter.api.Test
    void isEqual1() {
        Root root1 = getExampleTree();
        Root root2 = getExampleTree();
        assertTrue(Node.isEqual(root1, root2));
    }

    @org.junit.jupiter.api.Test
    void isEqual2() {
        Node node = new Root(1);
        Node root = node;
        for (int i = 0; i < 10; i++) {
            node.setLeftChild(new Node(1));
            node.setRightChild(new Node(2));
            node = node.getRightChild();
        }
        assertFalse(Node.isEqual(root, getExampleTree()));
    }

    @org.junit.jupiter.api.Test
    void isEqual3() {
        Node node = new Root(1);
        Node root1 = node;
        for (int i = 0; i < 10; i++) {
            node.setLeftChild(new Node(1));
            node.setRightChild(new Node(2));
            node = node.getRightChild();
        }
        assertFalse(Node.isEqual(root1, null));
    }

    @org.junit.jupiter.api.Test
    void isEqual4() {
        Root root1 = new Root(3);
        Root root2 = new Root(4);
        assertFalse(Node.isEqual(root1, root2));
    }

    @org.junit.jupiter.api.Test
    void isEqual5() {
        Root root1 = new Root(3);
        Root root2 = new Root(3);
        assertTrue(Node.isEqual(root1, root2));
    }
}
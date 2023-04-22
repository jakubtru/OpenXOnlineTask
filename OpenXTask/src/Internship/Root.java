package Internship;

/**
 * Klasa root dziedziczy po Node i została utworzona jedynie ze względu na przejrzystość programu:
 * jako że nie zdecydowałem się na utworzenie klasy zawierającej całe drzewo w strukturze danych uznałem,
 * że warto by każde drzewo posiadało jeden węzeł z klasy Root, co ma implikować że jest to korzeń.
 */

public class Root extends Node {
    public Root(int value) {
        super(value);
    }
}

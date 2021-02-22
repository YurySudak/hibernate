package org.example.hierarchy.table;

import org.example.SessionManager;

public class DemoTable {

    public static void main( String[] args ) {
        Product blackTea = new Drink("Black Tea", 1.0, 200, false);
        Product mocaccino = new Drink("Mocaccino", 2.5, 300, true);
        Product withChicken = new Donner("With Chicken", 6.0, "XL", "sweet and sour");
        Product vegetarian = new Donner("Vegetarian", 3.0, "L", "cheesy");

        SessionManager.saveAll(blackTea, mocaccino, withChicken, vegetarian);

        SessionManager.loadAll(Product.class).forEach(System.out::println);
    }
}

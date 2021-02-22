package org.example.hierarchy.mapped;

import org.example.SessionManager;

public class DemoMapped {

    public static void main( String[] args ) {
        Drink blackTea = new Drink("Black Tea", 1.0, 200, false);
        Drink mocaccino = new Drink("Mocaccino", 2.5, 300, true);
        Donner withChicken = new Donner("With Chicken", 6.0, "XL", "sweet and sour");
        Donner vegetarian = new Donner("Vegetarian", 3.0, "L", "cheesy");

        SessionManager.saveAll(blackTea, mocaccino, withChicken, vegetarian);

        SessionManager.loadAll(Drink.class).forEach(System.out::println);
        SessionManager.loadAll(Donner.class).forEach(System.out::println);
    }
}

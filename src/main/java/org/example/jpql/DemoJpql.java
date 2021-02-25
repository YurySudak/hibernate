package org.example.jpql;

import org.example.Color;

public class DemoJpql {
    public static void main(String[] args) {
        Product blackTea = new Drink("Black Tea", 1.0, 200, false);
        Product espresso = new Drink("Espresso", 2.0, 100, false);
        Product mocaccino = new Drink("Mocaccino", 2.5, 300, true);
        Product withChicken = new Donner("With Chicken", 6.0, "XL", "sweet and sour");
        Product vegetarian = new Donner("Vegetarian", 3.0, "L", "cheesy");
        Product banditos = new Donner("Banditos", 8.0, "XXL", "tomato");

        JpqlManager.saveAll(blackTea, mocaccino, espresso, withChicken, vegetarian, banditos);

        JpqlManager.loadAll(Product.class).forEach(System.out::println);
        JpqlManager.loadNames(Product.class).forEach(DemoJpql::printGreen);
        JpqlManager.loadCostLessThen(Product.class, 4).forEach(System.out::println);
        JpqlManager.loadLike(Product.class, "B%").forEach(DemoJpql::printGreen);

        JpqlManager.updateCost(Product.class, 5.5, "With Chicken");
        printGreen(JpqlManager.load(Product.class, "With Chicken").toString());
    }

    private static void printGreen(String string) {
        System.out.println(Color.GREEN + string + Color.RESET);
    }
}

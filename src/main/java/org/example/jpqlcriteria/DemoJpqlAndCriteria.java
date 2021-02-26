package org.example.jpqlcriteria;

import org.example.Color;

public class DemoJpqlAndCriteria {
    public static void main(String[] args) {
        Product blackTea = new Drink("Black Tea", 1.0, 200, false);
        Product espresso = new Drink("Espresso", 2.0, 100, false);
        Product mocaccino = new Drink("Mocaccino", 2.5, 300, true);
        Product withChicken = new Donner("With Chicken", 6.0, "XL", "sweet and sour");
        Product vegetarian = new Donner("Vegetarian", 3.0, "L", "cheesy");
        Product banditos = new Donner("Banditos", 8.0, "XXL", "tomato");

        JpqlManager.saveAll(blackTea, mocaccino, espresso, withChicken, vegetarian, banditos);

        Class clazz = Product.class;

        JpqlManager.loadAll(clazz).forEach(System.out::println);
        JpqlManager.loadNames(clazz).forEach(DemoJpqlAndCriteria::printGreen);
        JpqlManager.loadCostLessThen(clazz, 4).forEach(System.out::println);
        JpqlManager.loadLike(clazz, "B%").forEach(DemoJpqlAndCriteria::printGreen);
        JpqlManager.updateCost(clazz, 5.5, "With Chicken");
        printGreen(JpqlManager.load(clazz, "With Chicken").toString());


        CriteriaManager.loadAll(clazz).forEach(System.out::println);
        CriteriaManager.loadNames(clazz).forEach(p -> printGreen(p.getName()));
        CriteriaManager.loadCostLessThen(clazz, 3).forEach(System.out::println);
        CriteriaManager.loadLike(clazz, "%o").forEach(p -> printGreen(p.getName()));
        CriteriaManager.updateCost(clazz, 3.5, "Vegetarian");
        printGreen(CriteriaManager.load(clazz, "Vegetarian").toString());
    }

    private static void printGreen(String string) {
        System.out.println(Color.GREEN + string + Color.RESET);
    }
}

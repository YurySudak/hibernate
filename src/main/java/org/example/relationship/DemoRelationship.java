package org.example.relationship;

import org.example.SessionManager;

import java.util.List;

public class DemoRelationship {
    public static void main(String[] args) {

        City minsk = new City("Minsk");
        City gomel = new City("Gomel");

        SessionManager.saveAll(minsk, gomel);

        Person ivan = new Person("Ivan", new Info("222-33-44", "ivan@gmail.com"), minsk);
        Person fedor = new Person("Fedor", new Info("222-55-55", "fedor@gmail.com"), minsk);
        Person denis = new Person("Denis", new Info("222-66-66", "denis@gmail.com"), gomel);

        SessionManager.saveAll(ivan, fedor, denis);

        ivan.setFriends(List.of(fedor, denis));
        fedor.setFriends(List.of(ivan));
        denis.setFriends(List.of(ivan));

        SessionManager.updateAll(ivan, fedor, denis);

        SessionManager.loadAll(Person.class).forEach(System.out::println);
    }
}

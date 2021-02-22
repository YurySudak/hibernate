package org.example.relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.Color;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Info info;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> friends = new ArrayList<>();

    public Person(String name, Info info, City city) {
        this.name = name;
        this.info = info;
        this.city = city;
    }

    @Override
    public String toString() {
        return Color.BLUE + "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info=" + info +
                ", city=" + city +
                ", friends=" + friends.stream().map(Person::getName).collect(Collectors.joining(", ")) +
                '}' + Color.RESET;
    }
}

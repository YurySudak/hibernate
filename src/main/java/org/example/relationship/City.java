package org.example.relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Color;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> citizens;

    public City(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return Color.GREEN + "City{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}' + Color.RESET;
    }
}

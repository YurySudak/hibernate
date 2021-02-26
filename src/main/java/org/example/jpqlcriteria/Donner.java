package org.example.jpqlcriteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.Color;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("donner")
public class Donner extends Product {
    private String size;
    private String sauce;

    public Donner(String name, double cost, String size, String sauce) {
        super(name, cost);
        this.size = size;
        this.sauce = sauce;
    }

    @Override
    public String toString() {
        return Color.BLUE +
                "Donner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", size='" + size + '\'' +
                ", sauce='" + sauce + '\'' +
                '}' + Color.RESET;
    }
}

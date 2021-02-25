package org.example.jpql;

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
@DiscriminatorValue("drink")
public class Drink extends Product {
    private int volume;
    private boolean sugar;

    public Drink(String name, double cost, int volume, boolean sugar) {
        super(name, cost);
        this.volume = volume;
        this.sugar = sugar;
    }

    @Override
    public String toString() {
        return Color.BLUE +
                "Drink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", volume=" + volume +
                ", sugar=" + sugar +
                '}' + Color.RESET;
    }
}

package lajusticia.alejandro.zoo.food.domain.entity;

import lombok.Getter;

@Getter
public class Food {

    private final String name;

    public Food(final String name) {
        this.name = name;
    }

}

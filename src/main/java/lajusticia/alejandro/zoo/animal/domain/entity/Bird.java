package lajusticia.alejandro.zoo.animal.domain.entity;

import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Bird extends Animal {

    protected final float wingsLength;

    protected Bird(final AnimalIdentifier id, final String name, final Food favoriteFood, final float wingsLength) {
        super(id, name, favoriteFood);
        this.wingsLength = wingsLength;
    }

}

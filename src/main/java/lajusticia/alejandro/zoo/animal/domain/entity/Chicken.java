package lajusticia.alejandro.zoo.animal.domain.entity;

import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import lombok.Getter;

@Getter
public class Chicken extends Bird {

    private final boolean broiler;

    public Chicken(
            final AnimalIdentifier id,
            final String name,
            final Food favoriteFood,
            final float wingsLength,
            final boolean broiler)
    {
        super(id, name, favoriteFood, wingsLength);
        this.broiler = broiler;
    }

    @Override
    public String toString() {
        return "Chicken{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", favoriteFood=" + favoriteFood.getName() +
                ", wingsLength=" + wingsLength +
                ", broiler=" + broiler +
                ", " + printFriends() +
                '}';
    }
}

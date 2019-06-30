package lajusticia.alejandro.zoo.animal.domain.entity;

import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import lombok.Getter;

@Getter
public class Parrot extends Bird {

    private final boolean speaker;

    public Parrot(
            final AnimalIdentifier id,
            final String name,
            final Food favoriteFood,
            final float wingsLength,
            final boolean speaker)
    {
        super(id, name, favoriteFood, wingsLength);
        this.speaker = speaker;
    }

    @Override
    public String toString() {
        return "Parrot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", favoriteFood=" + favoriteFood.getName() +
                ", wingsLength=" + wingsLength +
                ", speaker=" + speaker +
                ", " + printFriends() +
                '}';
    }
}

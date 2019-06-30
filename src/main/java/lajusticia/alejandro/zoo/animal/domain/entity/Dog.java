package lajusticia.alejandro.zoo.animal.domain.entity;

import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.animal.domain.type.DogType;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import lombok.Getter;

@Getter
public class Dog extends Animal {

    private final DogType type;

    public Dog(final AnimalIdentifier id, final String name, final Food favoriteFood, final DogType type) {
        super(id, name, favoriteFood);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", favoriteFood=" + favoriteFood.getName() +
                ", type=" + type +
                ", " + printFriends() +
                '}';
    }
}

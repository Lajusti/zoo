package lajusticia.alejandro.zoo.animal.domain.entity;

import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class Animal {

    protected final AnimalIdentifier id;
    protected final String name;
    protected final Food favoriteFood;
    protected final List<Animal> friends;

    protected Animal(final AnimalIdentifier id, final String name, final Food favoriteFood) {
        this.id = id;
        this.name = name;
        this.favoriteFood = favoriteFood;
        friends = new ArrayList<>();
    }

    public void addFriend(Animal friendToAdd) {
        friends.remove(friendToAdd);
        friends.add(friendToAdd);
    }

    public void removeFriend(Animal friendToBeRemoved) {
        friends.remove(friendToBeRemoved);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Animal animal = (Animal) object;
        return id.equals(animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    protected String printFriends() {
        StringBuilder friends = new StringBuilder("friends=[");

        for (int i = 0; i < this.friends.size(); i++) {
            if (i > 0) {
                friends.append(", ");
            }
            friends.append(this.friends.get(i).getName());
        }

        friends.append("]");
        return friends.toString();
    }
}

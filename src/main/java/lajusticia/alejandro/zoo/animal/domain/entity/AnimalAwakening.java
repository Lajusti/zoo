package lajusticia.alejandro.zoo.animal.domain.entity;

import java.util.Objects;
import java.util.Optional;

import static lajusticia.alejandro.zoo.animal.domain.utils.AnimalOptionalFactory.buildOptional;

public class AnimalAwakening {

    private final Animal animal;

    private Animal friendNew;
    private Animal friendLost;

    public AnimalAwakening(final Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setFriendNew(Animal friendNew) {
        this.friendNew = friendNew;
        animal.addFriend(friendNew);
    }

    public Optional<Animal> getFriendNew() {
        return buildOptional(friendNew);
    }

    public void setFriendLost(Animal friendLost) {
        this.friendLost = friendLost;
        animal.removeFriend(friendLost);
    }

    public Optional<Animal> getFriendLost() {
        return buildOptional(friendLost);
    }

    public AwakeningResult getResult() {
        return new AwakeningResult(animal, friendNew, friendLost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalAwakening that = (AnimalAwakening) o;
        return Objects.equals(animal, that.animal) &&
                Objects.equals(friendNew, that.friendNew) &&
                Objects.equals(friendLost, that.friendLost);
    }

}

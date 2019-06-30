package lajusticia.alejandro.zoo.animal.domain.entity;

import java.util.Optional;

import static lajusticia.alejandro.zoo.animal.domain.utils.AnimalOptionalFactory.buildOptional;

public class AwakeningResult {

    private final Animal animal;
    private final Animal friendNew;
    private final Animal friendLost;

    AwakeningResult(Animal animal, Animal newFriend, Animal friendLost) {
        this.animal = animal;
        this.friendNew = newFriend;
        this.friendLost = friendLost;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Optional<Animal> getFriendNew() {
        return buildOptional(friendNew);
    }

    public Optional<Animal> getFriendLost() {
        return buildOptional(friendLost);
    }

}

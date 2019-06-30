package lajusticia.alejandro.zoo.animal.domain.utils;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;

import java.util.Optional;

public class AnimalOptionalFactory {

    public static Optional<Animal> buildOptional(Animal animal) {
        if (animal == null) {
            return Optional.empty();
        }
        return Optional.of(animal);
    }

    private AnimalOptionalFactory() {

    }

}

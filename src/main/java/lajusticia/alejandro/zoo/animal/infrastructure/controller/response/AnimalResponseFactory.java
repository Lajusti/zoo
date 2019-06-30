package lajusticia.alejandro.zoo.animal.infrastructure.controller.response;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lajusticia.alejandro.zoo.animal.domain.entity.Chicken;
import lajusticia.alejandro.zoo.animal.domain.entity.Dog;
import lajusticia.alejandro.zoo.animal.domain.entity.Parrot;

public class AnimalResponseFactory {

    public static AnimalResponse buildResponseOfAnimal(Animal animal) {
        if (animal instanceof Dog) {
            return new DogResponse((Dog) animal);
        }

        if (animal instanceof Parrot) {
            return new ParrotResponse((Parrot) animal);
        }

        return new ChickenResponse((Chicken) animal);
    }

    private AnimalResponseFactory() {

    }

}

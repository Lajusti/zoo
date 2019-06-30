package lajusticia.alejandro.zoo.animal.infrastructure.repository;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lajusticia.alejandro.zoo.animal.domain.entity.Chicken;
import lajusticia.alejandro.zoo.animal.domain.entity.Dog;
import lajusticia.alejandro.zoo.animal.domain.entity.Parrot;

class AnimalEntity {

    private Animal animal;

    void setAnimal(Animal animal) {
        this.animal = animal;
    }

    Animal getAnimal() {
        Animal animalToRetrieve;
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            animalToRetrieve = new Dog(dog.getId(), dog.getName(), dog.getFavoriteFood(), dog.getType());
        } else if (animal instanceof Parrot) {
            Parrot parrot = (Parrot) animal;
            animalToRetrieve = new Parrot(
                    parrot.getId(),
                    parrot.getName(),
                    parrot.getFavoriteFood(),
                    parrot.getWingsLength(),
                    parrot.isSpeaker()
            );
        } else {
            Chicken chicken = (Chicken) animal;
            animalToRetrieve = new Chicken(
                    chicken.getId(),
                    chicken.getName(),
                    chicken.getFavoriteFood(),
                    chicken.getWingsLength(),
                    chicken.isBroiler()
            );
        }

        animal.getFriends().forEach(animalToRetrieve::addFriend);
        return animalToRetrieve;
    }

}

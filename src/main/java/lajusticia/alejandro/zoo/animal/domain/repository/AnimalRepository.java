package lajusticia.alejandro.zoo.animal.domain.repository;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;

import java.util.List;

public interface AnimalRepository {

    /**
     * Build a new id of the entity ID
     *
     * @return a new id of {@link AnimalIdentifier}
     */
    AnimalIdentifier buildNewAnimalId();

    /**
     * Obtain all the animals in the system
     *
     * @return list with all {@link Animal} of the system
     */
    List<Animal> getAll();

    /**
     * Save all the animals of the param in the system, if an animal does not exist in the system it will be created
     *
     * @param animals to save in the system
     */
    void saveAll(List<Animal> animals);

    /**
     * Save the animal of the param in the system, if the animal does not exist in the system it will be created
     *
     * @param animal to save in the system
     */
    void save(Animal animal);

}

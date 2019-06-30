package lajusticia.alejandro.zoo.animal.application.read;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lajusticia.alejandro.zoo.animal.domain.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObtainAllAnimals {

    private final AnimalRepository animalRepository;

    public ObtainAllAnimals(final AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> obtainAllAnimalsInZoo() {
        return animalRepository.getAll();
    }

}

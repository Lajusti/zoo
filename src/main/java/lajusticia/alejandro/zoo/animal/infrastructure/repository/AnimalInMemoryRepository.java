package lajusticia.alejandro.zoo.animal.infrastructure.repository;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.animal.domain.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnimalInMemoryRepository implements AnimalRepository {

    List<AnimalEntity> animals = new ArrayList<>();
    Map<Integer, AnimalEntity> animalIndex = new HashMap<>();

    @Override
    public AnimalIdentifier buildNewAnimalId() {
        return new AnimalIdentifier(UUID.randomUUID().toString());
    }

    @Override
    public List<Animal> getAll() {
        return animals.stream()
                .map(AnimalEntity::getAnimal)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<Animal> animals) {
        animals.forEach(this::save);
    }

    @Override
    public void save(Animal animal) {
        int hashOfId = animal.getId().hashCode();
        AnimalEntity animalEntity = animalIndex.get(hashOfId);
        if (animalEntity == null) {
            animalEntity = new AnimalEntity();
            animals.add(animalEntity);
            animalIndex.put(hashOfId, animalEntity);
        }
        animalEntity.setAnimal(animal);
    }

}

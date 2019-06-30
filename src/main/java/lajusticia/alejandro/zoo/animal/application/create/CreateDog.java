package lajusticia.alejandro.zoo.animal.application.create;

import lajusticia.alejandro.zoo.animal.domain.entity.Dog;
import lajusticia.alejandro.zoo.animal.application.event.NewDogCreated;
import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.animal.domain.repository.AnimalRepository;
import lajusticia.alejandro.zoo.animal.domain.type.DogType;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import lajusticia.alejandro.zoo.food.domain.repository.FoodRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CreateDog {

    private final AnimalRepository animalRepository;
    private final FoodRepository foodRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CreateDog(
            AnimalRepository animalRepository,
            FoodRepository foodRepository,
            ApplicationEventPublisher applicationEventPublisher)
    {
        this.animalRepository = animalRepository;
        this.foodRepository = foodRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Dog create(String name, String foodName, DogType dogType) {
        Food food = foodRepository.getFoodByName(foodName);
        AnimalIdentifier id = animalRepository.buildNewAnimalId();

        Dog dog = new Dog(id, name, food, dogType);

        applicationEventPublisher.publishEvent(new NewDogCreated(this, dog));

        animalRepository.save(dog);

        return dog;
    }

}

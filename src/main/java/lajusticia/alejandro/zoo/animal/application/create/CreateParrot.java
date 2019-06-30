package lajusticia.alejandro.zoo.animal.application.create;

import lajusticia.alejandro.zoo.animal.domain.entity.Parrot;
import lajusticia.alejandro.zoo.animal.application.event.NewParrotCreated;
import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.animal.domain.repository.AnimalRepository;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import lajusticia.alejandro.zoo.food.domain.repository.FoodRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CreateParrot {

    private final AnimalRepository animalRepository;
    private final FoodRepository foodRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CreateParrot(
            AnimalRepository animalRepository,
            FoodRepository foodRepository,
            ApplicationEventPublisher applicationEventPublisher)
    {
        this.animalRepository = animalRepository;
        this.foodRepository = foodRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Parrot create(String name, String foodName, float wingsLength, boolean canSpeak) {
        Food food = foodRepository.getFoodByName(foodName);
        AnimalIdentifier id = animalRepository.buildNewAnimalId();

        Parrot parrot = new Parrot(id, name, food, wingsLength, canSpeak);

        applicationEventPublisher.publishEvent(new NewParrotCreated(this, parrot));

        animalRepository.save(parrot);

        return parrot;
    }

}

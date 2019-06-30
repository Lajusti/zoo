package lajusticia.alejandro.zoo.animal.application.create;

import lajusticia.alejandro.zoo.animal.domain.entity.Chicken;
import lajusticia.alejandro.zoo.animal.application.event.NewChickenCreated;
import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.animal.domain.repository.AnimalRepository;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import lajusticia.alejandro.zoo.food.domain.repository.FoodRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CreateChicken {

    private final AnimalRepository animalRepository;
    private final FoodRepository foodRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CreateChicken(
            AnimalRepository animalRepository,
            FoodRepository foodRepository,
            ApplicationEventPublisher applicationEventPublisher)
    {
        this.animalRepository = animalRepository;
        this.foodRepository = foodRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Chicken create(String name, String foodName, float wingsLength, boolean isBroiler) {
        Food food = foodRepository.getFoodByName(foodName);
        AnimalIdentifier id = animalRepository.buildNewAnimalId();

        Chicken chicken = new Chicken(id, name, food, wingsLength, isBroiler);

        applicationEventPublisher.publishEvent(new NewChickenCreated(this, chicken));

        animalRepository.save(chicken);

        return chicken;
    }

}

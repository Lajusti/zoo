package lajusticia.alejandro.zoo.animal.application.update;

import lajusticia.alejandro.zoo.animal.domain.entity.*;
import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.animal.domain.repository.AnimalRepository;
import lajusticia.alejandro.zoo.animal.domain.service.AwakeningService;
import lajusticia.alejandro.zoo.animal.domain.type.DogType;
import lajusticia.alejandro.zoo.animal.infrastructure.AnimalAwakeningSelectorForTest;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lajusticia.alejandro.zoo.utils.TestUtils.validateListWithExpected;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = { NewDay.class, AwakeningService.class, AnimalAwakeningSelectorForTest.class })
@ExtendWith(SpringExtension.class)
class NewDayTest {

    @MockBean
    private AnimalRepository animalRepository;

    @MockBean
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private NewDay newDay;

    @Test
    @DisplayName("Awake the animals without relations, all will gain one friend")
    void awakeAllAnimalsInZoo_animalsWithoutRelations(){
        when(animalRepository.getAll())
                .thenReturn(buildBaseAnimals());

        List<Animal> result = newDay.awakeAllAnimalsInZoo();


        verify(animalRepository, times(1))
                .getAll();

        verify(animalRepository, times(1))
                .saveAll(buildBaseAnimals());

        List<Animal> originalAnimals= buildBaseAnimals();

        originalAnimals.get(0).addFriend(originalAnimals.get(1));
        originalAnimals.get(1).addFriend(originalAnimals.get(0));
        originalAnimals.get(2).addFriend(originalAnimals.get(3));
        originalAnimals.get(3).addFriend(originalAnimals.get(2));

        validateListWithExpected(result, originalAnimals);
    }

    @Test
    @DisplayName("Awake the animals with all the relations possible, all will lose one friend")
    void awakeAllAnimalsInZoo_allAnimalsFriendsOfAnother() {
        List<Animal> originalAnimals = buildBaseAnimals();
        originalAnimals.get(0).addFriend(originalAnimals.get(1));
        originalAnimals.get(0).addFriend(originalAnimals.get(2));
        originalAnimals.get(0).addFriend(originalAnimals.get(3));
        originalAnimals.get(1).addFriend(originalAnimals.get(0));
        originalAnimals.get(1).addFriend(originalAnimals.get(2));
        originalAnimals.get(1).addFriend(originalAnimals.get(3));
        originalAnimals.get(2).addFriend(originalAnimals.get(0));
        originalAnimals.get(2).addFriend(originalAnimals.get(1));
        originalAnimals.get(2).addFriend(originalAnimals.get(3));
        originalAnimals.get(3).addFriend(originalAnimals.get(0));
        originalAnimals.get(3).addFriend(originalAnimals.get(1));
        originalAnimals.get(3).addFriend(originalAnimals.get(2));

        when(animalRepository.getAll())
                .thenReturn(originalAnimals);

        List<Animal> result = newDay.awakeAllAnimalsInZoo();

        verify(animalRepository, times(1))
                .getAll();

        verify(animalRepository, times(1))
                .saveAll(originalAnimals);

        originalAnimals = buildBaseAnimals();
        originalAnimals.get(0).addFriend(originalAnimals.get(2));
        originalAnimals.get(0).addFriend(originalAnimals.get(3));
        originalAnimals.get(1).addFriend(originalAnimals.get(2));
        originalAnimals.get(1).addFriend(originalAnimals.get(3));
        originalAnimals.get(2).addFriend(originalAnimals.get(0));
        originalAnimals.get(2).addFriend(originalAnimals.get(1));
        originalAnimals.get(3).addFriend(originalAnimals.get(0));
        originalAnimals.get(3).addFriend(originalAnimals.get(1));

        validateListWithExpected(result, originalAnimals);
    }

    @Test
    @DisplayName("Awake the animals the first and the third will lose one friend but the second can not lose their friend")
    void awakeAllAnimalsInZoo_secondAnimalCanNotLoseFriend() {
        List<Animal> originalAnimals = buildBaseAnimals();
        originalAnimals.get(0).addFriend(originalAnimals.get(2));
        originalAnimals.get(1).addFriend(originalAnimals.get(2));
        originalAnimals.get(2).addFriend(originalAnimals.get(0));
        originalAnimals.get(2).addFriend(originalAnimals.get(1));

        when(animalRepository.getAll())
                .thenReturn(originalAnimals);

        List<Animal> result = newDay.awakeAllAnimalsInZoo();

        verify(animalRepository, times(1))
                .getAll();

        verify(animalRepository, times(1))
                .saveAll(originalAnimals);

        originalAnimals = buildBaseAnimals();
        originalAnimals.get(0).addFriend(originalAnimals.get(1));
        originalAnimals.get(1).addFriend(originalAnimals.get(2));
        originalAnimals.get(1).addFriend(originalAnimals.get(0));
        originalAnimals.get(2).addFriend(originalAnimals.get(1));
        originalAnimals.get(2).addFriend(originalAnimals.get(3));
        originalAnimals.get(3).addFriend(originalAnimals.get(2));

        validateListWithExpected(result, originalAnimals);
    }

    @Test
    @DisplayName("Awake the animals the first and the third will win one friend but the second can not win one friend")
    void awakeAllAnimalsInZoo_secondAnimalCanNotObtainFriend() {
        List<Animal> originalAnimals = buildBaseAnimals();
        originalAnimals.get(0).addFriend(originalAnimals.get(1));
        originalAnimals.get(0).addFriend(originalAnimals.get(3));
        originalAnimals.get(1).addFriend(originalAnimals.get(0));
        originalAnimals.get(1).addFriend(originalAnimals.get(3));
        originalAnimals.get(2).addFriend(originalAnimals.get(3));
        originalAnimals.get(3).addFriend(originalAnimals.get(0));
        originalAnimals.get(3).addFriend(originalAnimals.get(1));
        originalAnimals.get(3).addFriend(originalAnimals.get(2));

        when(animalRepository.getAll())
                .thenReturn(originalAnimals);

        List<Animal> result = newDay.awakeAllAnimalsInZoo();

        verify(animalRepository, times(1))
                .getAll();

        verify(animalRepository, times(1))
                .saveAll(originalAnimals);

        originalAnimals = buildBaseAnimals();
        originalAnimals.get(0).addFriend(originalAnimals.get(3));
        originalAnimals.get(0).addFriend(originalAnimals.get(2));
        originalAnimals.get(1).addFriend(originalAnimals.get(3));
        originalAnimals.get(2).addFriend(originalAnimals.get(0));
        originalAnimals.get(3).addFriend(originalAnimals.get(0));
        originalAnimals.get(3).addFriend(originalAnimals.get(1));

        validateListWithExpected(result, originalAnimals);
    }

    private List<Animal> buildBaseAnimals() {
        return Arrays.asList(
                new Dog(new AnimalIdentifier("ID1"), "DOG1", new Food("FOOD1"), DogType.HUNTING_DOG),
                new Chicken(new AnimalIdentifier("ID2"), "CHICKEN", new Food("FOOD4"), 7.78f, true),
                new Parrot(new AnimalIdentifier("ID3"), "PARROT1", new Food("FOOD4"), 0.01f, false),
                new Parrot(new AnimalIdentifier("ID4"), "PARROT2", new Food("FOOD3"), 0.01f, true)
        );
    }


    private List<AnimalAwakening> buildBaseAnimalsAwakening() {
        return buildBaseAnimals().stream()
                .map(AnimalAwakening::new)
                .collect(Collectors.toList());
    }
}

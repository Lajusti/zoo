package lajusticia.alejandro.zoo.animal.application.read;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lajusticia.alejandro.zoo.animal.domain.entity.Chicken;
import lajusticia.alejandro.zoo.animal.domain.entity.Dog;
import lajusticia.alejandro.zoo.animal.domain.entity.Parrot;
import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.animal.domain.repository.AnimalRepository;
import lajusticia.alejandro.zoo.animal.domain.type.DogType;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static lajusticia.alejandro.zoo.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = ObtainAllAnimals.class)
@ExtendWith(SpringExtension.class)
class ObtainAllAnimalsTest {

    @MockBean
    private AnimalRepository animalRepository;

    @Autowired
    private ObtainAllAnimals obtainAllAnimals;

    @Test
    @DisplayName("Obtain a list of animals")
    void obtainAllAnimalsInZoo() {
        List<Animal> expectedAnimals = Arrays.asList(
                new Dog(new AnimalIdentifier("ID1"), "DOG1", new Food("FOOD1"), DogType.HUNTING_DOG),
                new Dog(new AnimalIdentifier("ID2"), "DOG2", new Food("FOOD2"), DogType.SPORT_DOG),
                new Dog(new AnimalIdentifier("ID3"), "DOG3", new Food("FOOD1"), DogType.WORKING_DOG),
                new Chicken(new AnimalIdentifier("ID4"), "CHICKEN", new Food("FOOD3"), 0.25f, false),
                new Chicken(new AnimalIdentifier("ID5"), "CHICKEN", new Food("FOOD4"), 7.78f, true),
                new Parrot(new AnimalIdentifier("ID6"), "PARROT1", new Food("FOOD4"), 0.01f, false),
                new Parrot(new AnimalIdentifier("ID7"), "PARROT2", new Food("FOOD3"), 0.01f, true)
        );

        when(animalRepository.getAll())
                .thenReturn(expectedAnimals);

        List<Animal> returnedAnimals = obtainAllAnimals.obtainAllAnimalsInZoo();

        verify(animalRepository, times(1))
                .getAll();

        validateListWithExpected(returnedAnimals, expectedAnimals);
    }

    @Test
    @DisplayName("Obtain a list of animals with friends")
    void obtainAllAnimalsInZoo_withFriends() {
        Dog dog1 = new Dog(new AnimalIdentifier("ID1"), "DOG1", new Food("FOOD1"), DogType.HUNTING_DOG);
        Dog dog2 = new Dog(new AnimalIdentifier("ID2"), "DOG2", new Food("FOOD2"), DogType.SPORT_DOG);
        Dog dog3 = new Dog(new AnimalIdentifier("ID3"), "DOG3", new Food("FOOD1"), DogType.WORKING_DOG);
        Chicken chicken1 = new Chicken(new AnimalIdentifier("ID4"), "CHICKEN", new Food("FOOD3"), 0.25f, false);
        Chicken chicken2 = new Chicken(new AnimalIdentifier("ID5"), "CHICKEN", new Food("FOOD4"), 7.78f, true);
        Parrot parrot1 = new Parrot(new AnimalIdentifier("ID6"), "PARROT1", new Food("FOOD4"), 0.01f, false);
        Parrot parrot2 = new Parrot(new AnimalIdentifier("ID7"), "PARROT2", new Food("FOOD3"), 0.01f, true);

        dog1.addFriend(dog2);
        dog1.addFriend(dog3);
        dog2.addFriend(dog1);
        dog3.addFriend(dog1);

        chicken1.addFriend(parrot1);
        chicken1.addFriend(parrot2);
        parrot1.addFriend(chicken1);
        parrot2.addFriend(chicken1);
        chicken2.addFriend(parrot1);
        parrot1.addFriend(chicken2);

        List<Animal> expectedAnimals = Arrays.asList(
                dog1,
                dog2,
                dog3,
                chicken1,
                chicken2,
                parrot1,
                parrot2
        );

        when(animalRepository.getAll())
                .thenReturn(expectedAnimals);

        List<Animal> returnedAnimals = obtainAllAnimals.obtainAllAnimalsInZoo();

        verify(animalRepository, times(1))
                .getAll();

        validateListWithExpected(returnedAnimals, expectedAnimals);
    }

    @Test
    @DisplayName("Obtain a empty list of animals")
    void obtainAllAnimalsInZoo_emptyList() {
        List<Animal> expectedAnimals = Collections.emptyList();

        when(animalRepository.getAll())
                .thenReturn(expectedAnimals);

        List<Animal> returnedAnimals = obtainAllAnimals.obtainAllAnimalsInZoo();

        verify(animalRepository, times(1))
                .getAll();

        assertNotNull(returnedAnimals);
        assertEquals(expectedAnimals.size(), returnedAnimals.size());
        assertTrue(returnedAnimals.isEmpty());
    }

}

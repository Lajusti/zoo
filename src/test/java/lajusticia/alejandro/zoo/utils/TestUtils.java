package lajusticia.alejandro.zoo.utils;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lajusticia.alejandro.zoo.animal.domain.entity.Chicken;
import lajusticia.alejandro.zoo.animal.domain.entity.Dog;
import lajusticia.alejandro.zoo.animal.domain.entity.Parrot;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestUtils {

    public static void validateParrotWithExpected(Parrot parrot, Parrot expected) {
        assertNotNull(parrot);
        assertEquals(expected.getId(), parrot.getId());
        assertEquals(expected.getFavoriteFood().getName(), parrot.getFavoriteFood().getName());
        assertEquals(expected.getName(), parrot.getName());
        assertEquals(expected.getWingsLength(), parrot.getWingsLength());
        assertEquals(expected.isSpeaker(), parrot.isSpeaker());
        assertNotNull(parrot.getFriends());
        assertEquals(expected.getFriends(), parrot.getFriends());
    }

    public static void validateChickenWithExpected(Chicken chicken, Chicken expected) {
        assertNotNull(chicken);
        assertEquals(expected.getId(), chicken.getId());
        assertEquals(expected.getFavoriteFood().getName(), chicken.getFavoriteFood().getName());
        assertEquals(expected.getName(), chicken.getName());
        assertEquals(expected.getWingsLength(), chicken.getWingsLength());
        assertEquals(expected.isBroiler(), chicken.isBroiler());
        assertNotNull(chicken.getFriends());
        assertEquals(expected.getFriends(), chicken.getFriends());
    }

    public static void validateDogWithExpected(Dog dog, Dog expected) {
        assertNotNull(dog);
        assertEquals(expected.getId(), dog.getId());
        assertEquals(expected.getFavoriteFood().getName(), dog.getFavoriteFood().getName());
        assertEquals(expected.getName(), dog.getName());
        assertEquals(expected.getType().name(), dog.getType().name());
        assertNotNull(dog.getFriends());
        assertEquals(expected.getFriends(), dog.getFriends());
    }

    public static void validateListWithExpected(List<Animal> returnedAnimals, List<Animal> expectedAnimals) {
        assertNotNull(returnedAnimals);
        assertEquals(expectedAnimals.size(), returnedAnimals.size());

        for(int i = 0; i < expectedAnimals.size(); i++) {
            Animal expectedAnimal = expectedAnimals.get(i);
            Animal returnedAnimal = returnedAnimals.get(i);

            if (expectedAnimal instanceof Dog) {
                validateDogWithExpected((Dog) returnedAnimal, (Dog) expectedAnimal);
            }

            if (expectedAnimal instanceof Parrot) {
                validateParrotWithExpected((Parrot) returnedAnimal, (Parrot) expectedAnimal);
            }

            if (expectedAnimal instanceof Chicken) {
                validateChickenWithExpected((Chicken) returnedAnimal, (Chicken) expectedAnimal);
            }
        }
    }

    private TestUtils() {

    }

}

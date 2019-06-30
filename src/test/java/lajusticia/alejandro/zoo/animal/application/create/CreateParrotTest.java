package lajusticia.alejandro.zoo.animal.application.create;

import lajusticia.alejandro.zoo.animal.domain.entity.Parrot;
import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.animal.domain.repository.AnimalRepository;
import lajusticia.alejandro.zoo.food.domain.entity.Food;
import lajusticia.alejandro.zoo.food.domain.repository.FoodRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static lajusticia.alejandro.zoo.utils.TestUtils.validateParrotWithExpected;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = CreateParrot.class)
@ExtendWith(SpringExtension.class)
class CreateParrotTest {

    @MockBean
    private AnimalRepository animalRepository;

    @MockBean
    private FoodRepository foodRepository;

    @MockBean
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private CreateParrot createParrot;

    @Test
    @DisplayName("Test the creation of one parrot with data: id -> ID, name -> PARROT, food -> FOOD, " +
            "wingLength -> 0.2 and canSpeak -> false")
    void create_data1() {
        String id = "ID";
        String name = "PARROT";
        String food = "FOOD";
        float wingLength = 0.2f;
        boolean canSpeak = false;

        doTestWithData(id, name, food, wingLength, canSpeak);
    }

    @Test
    @DisplayName("Test the creation of one parrot with data: id -> ID_2, name -> PARROT_2, food -> FOOD_2, " +
            "wingLength -> 20.75 and canSpeak -> true")
    void create_data2() {
        String id = "ID_2";
        String name = "PARROT_2";
        String food = "FOOD_2";
        float wingLength = 20.75f;
        boolean canSpeak = true;

        doTestWithData(id, name, food, wingLength, canSpeak);
    }

    private void doTestWithData(String id, String name, String food, float wingLength, boolean canSpeak) {
        Food expectedFood = new Food(food);
        AnimalIdentifier expectedId = new AnimalIdentifier(id);

        Parrot expectedParrot = new Parrot(expectedId, name, expectedFood, wingLength, canSpeak);

        when(foodRepository.getFoodByName(food))
                .thenReturn(expectedFood);

        when(animalRepository.buildNewAnimalId())
                .thenReturn(expectedId);

        Parrot parrotCreated = createParrot.create(name, food, wingLength, canSpeak);

        verify(foodRepository, times(1))
                .getFoodByName(food);

        verify(animalRepository, times(1))
                .buildNewAnimalId();

        verify(animalRepository, times(1))
                .save(expectedParrot);

        validateParrotWithExpected(parrotCreated, expectedParrot);
    }

}

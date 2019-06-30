package lajusticia.alejandro.zoo.animal.application.create;

import lajusticia.alejandro.zoo.animal.domain.entity.Chicken;
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

import static lajusticia.alejandro.zoo.utils.TestUtils.validateChickenWithExpected;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = CreateChicken.class)
@ExtendWith(SpringExtension.class)
class CreateChickenTest {

    @MockBean
    private AnimalRepository animalRepository;

    @MockBean
    private FoodRepository foodRepository;

    @MockBean
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private CreateChicken createChicken;

    @Test
    @DisplayName("Test the creation of one chicken with data: id -> ID, name -> CHICKEN, food -> FOOD, " +
            "wingLength -> 0.2 and broiler -> false")
    void create_data1() {
        String id = "ID";
        String name = "CHICKEN";
        String food = "FOOD";
        float wingLength = 0.2f;
        boolean isBroiler = false;

        doTestWithData(id, name, food, wingLength, isBroiler);
    }

    @Test
    @DisplayName("Test the creation of one chicken with data: id -> ID_2, name -> CHICKEN_2, food -> FOOD_2, " +
            "wingLength -> 20.75 and broiler -> true")
    void create_data2() {
        String id = "ID_2";
        String name = "CHICKEN_2";
        String food = "FOOD_2";
        float wingLength = 20.75f;
        boolean isBroiler = true;

        doTestWithData(id, name, food, wingLength, isBroiler);
    }

    private void doTestWithData(String id, String name, String food, float wingLength, boolean isBroiler) {
        Food expectedFood = new Food(food);
        AnimalIdentifier expectedId = new AnimalIdentifier(id);

        Chicken expectedChicken = new Chicken(expectedId, name, expectedFood, wingLength, isBroiler);

        when(foodRepository.getFoodByName(food))
                .thenReturn(expectedFood);

        when(animalRepository.buildNewAnimalId())
                .thenReturn(expectedId);

        Chicken chickenCreated = createChicken.create(name, food, wingLength, isBroiler);

        verify(foodRepository, times(1))
                .getFoodByName(food);

        verify(animalRepository, times(1))
                .buildNewAnimalId();

        verify(animalRepository, times(1))
                .save(expectedChicken);

        validateChickenWithExpected(chickenCreated, expectedChicken);
    }

}

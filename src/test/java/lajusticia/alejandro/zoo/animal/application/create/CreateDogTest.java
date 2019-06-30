package lajusticia.alejandro.zoo.animal.application.create;

import lajusticia.alejandro.zoo.animal.domain.entity.Dog;
import lajusticia.alejandro.zoo.animal.domain.identifier.AnimalIdentifier;
import lajusticia.alejandro.zoo.animal.domain.repository.AnimalRepository;
import lajusticia.alejandro.zoo.animal.domain.type.DogType;
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

import static lajusticia.alejandro.zoo.utils.TestUtils.validateDogWithExpected;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = CreateDog.class)
@ExtendWith(SpringExtension.class)
class CreateDogTest {

    @MockBean
    private AnimalRepository animalRepository;

    @MockBean
    private FoodRepository foodRepository;

    @MockBean
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private CreateDog createDog;

    @Test
    @DisplayName("Test the creation of one dog with data: id -> ID, name -> DOG, food -> FOOD" +
            " and dogType -> HUNTING_DOG")
    void create_data1() {
        String id = "ID";
        String name = "DOG";
        String food = "FOOD";
        DogType dogType = DogType.HUNTING_DOG;

        doTestWithData(id, name, food, dogType);
    }

    @Test
    @DisplayName("Test the creation of one dog with data: id -> ID_2, name -> DOG_2, food -> FOOD_2" +
            " and dogType -> SPORT_DOG")
    void create_data2() {
        String id = "ID_2";
        String name = "DOG_2";
        String food = "FOOD_2";
        DogType dogType = DogType.SPORT_DOG;

        doTestWithData(id, name, food, dogType);
    }

    @Test
    @DisplayName("Test the creation of one dog with data: id -> ID_3, name -> DOG_3, food -> FOOD_3" +
            " and dogType -> WORKING_DOG")
    void create_data3() {
        String id = "ID_3";
        String name = "DOG_3";
        String food = "FOOD_3";
        DogType dogType = DogType.WORKING_DOG;

        doTestWithData(id, name, food, dogType);
    }

    private void doTestWithData(String id, String name, String food, DogType dogType) {
        Food expectedFood = new Food(food);
        AnimalIdentifier expectedId = new AnimalIdentifier(id);

        Dog expectedDog = new Dog(expectedId, name, expectedFood, dogType);

        when(foodRepository.getFoodByName(food))
                .thenReturn(expectedFood);

        when(animalRepository.buildNewAnimalId())
                .thenReturn(expectedId);

        Dog dogCreated = createDog.create(name, food, dogType);

        verify(foodRepository, times(1))
                .getFoodByName(food);

        verify(animalRepository, times(1))
                .buildNewAnimalId();

        verify(animalRepository, times(1))
                .save(expectedDog);

        validateDogWithExpected(dogCreated, expectedDog);
    }

}

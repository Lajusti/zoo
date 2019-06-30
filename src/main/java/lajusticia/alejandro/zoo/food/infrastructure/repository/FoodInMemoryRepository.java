package lajusticia.alejandro.zoo.food.infrastructure.repository;

import lajusticia.alejandro.zoo.food.domain.entity.Food;
import lajusticia.alejandro.zoo.food.domain.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FoodInMemoryRepository implements FoodRepository {

    private Map<String, Food> foods = new HashMap<>();

    @Override
    public Food getFoodByName(String name) {
        Food food = foods.get(name);

        if (food == null) {
            food = new Food(name);
            foods.put(name, food);
        }

        return food;
    }
}

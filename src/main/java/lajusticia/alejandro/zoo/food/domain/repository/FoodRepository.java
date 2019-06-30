package lajusticia.alejandro.zoo.food.domain.repository;

import lajusticia.alejandro.zoo.food.domain.entity.Food;

public interface FoodRepository {

    Food getFoodByName(String name);

}

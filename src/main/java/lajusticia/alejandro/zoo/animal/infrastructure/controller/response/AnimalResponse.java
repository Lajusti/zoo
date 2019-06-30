package lajusticia.alejandro.zoo.animal.infrastructure.controller.response;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AnimalResponse {

    protected String type;
    private final String name;
    private final String favoriteFood;
    private final List<String> friends;

    protected AnimalResponse(Animal animal) {
         name = animal.getName();
         favoriteFood = animal.getFavoriteFood().getName();
         friends = new ArrayList<>();
         animal.getFriends().forEach(friend -> friends.add(friend.getName()));
    }

}
package lajusticia.alejandro.zoo.animal.infrastructure.controller.response;

import lajusticia.alejandro.zoo.animal.domain.entity.Dog;
import lombok.Getter;

@Getter
public class DogResponse extends AnimalResponse {

    private final String dogType;

    public DogResponse(Dog dog) {
        super(dog);
        type = "Dog";
        dogType = dog.getType().name();
    }

}

package lajusticia.alejandro.zoo.animal.infrastructure.controller.response;

import lajusticia.alejandro.zoo.animal.domain.entity.Bird;
import lombok.Getter;

@Getter
public abstract class BirdResponse extends AnimalResponse {

    private final float lengthOfWings;

    protected  BirdResponse(Bird bird) {
        super(bird);
        lengthOfWings = bird.getWingsLength();
    }

}

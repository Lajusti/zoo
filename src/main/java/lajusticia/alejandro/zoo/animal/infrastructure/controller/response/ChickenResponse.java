package lajusticia.alejandro.zoo.animal.infrastructure.controller.response;

import lajusticia.alejandro.zoo.animal.domain.entity.Chicken;
import lombok.Getter;

@Getter
public class ChickenResponse extends AnimalResponse {

    private final boolean broiler;

    public ChickenResponse(Chicken chicken) {
        super(chicken);
        type = "Chicken";
        broiler = chicken.isBroiler();
    }

}

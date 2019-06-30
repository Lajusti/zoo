package lajusticia.alejandro.zoo.animal.infrastructure.controller.response;

import lajusticia.alejandro.zoo.animal.domain.entity.Parrot;
import lombok.Getter;

@Getter
public class ParrotResponse extends BirdResponse {

    private final boolean speak;

    public ParrotResponse(Parrot parrot) {
        super(parrot);
        type = "Parrot";
        speak = parrot.isSpeaker();
    }

}

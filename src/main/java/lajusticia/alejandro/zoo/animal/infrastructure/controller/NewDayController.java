package lajusticia.alejandro.zoo.animal.infrastructure.controller;

import lajusticia.alejandro.zoo.animal.application.update.NewDay;
import lajusticia.alejandro.zoo.animal.infrastructure.controller.response.AnimalResponse;
import lajusticia.alejandro.zoo.animal.infrastructure.controller.response.AnimalResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/newday")
public class NewDayController {

    private final NewDay newDay;

    public NewDayController(final NewDay newDay) {
        this.newDay = newDay;
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> processNewDay() {
        return new ResponseEntity<>(
                newDay.awakeAllAnimalsInZoo().stream()
                        .map(AnimalResponseFactory::buildResponseOfAnimal)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

}

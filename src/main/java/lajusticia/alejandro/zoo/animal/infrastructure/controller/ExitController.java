package lajusticia.alejandro.zoo.animal.infrastructure.controller;

import lajusticia.alejandro.zoo.ExitApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/exit")
public class ExitController {

    private final ExitApplication exitApplication;

    public ExitController(ExitApplication exitApplication) {
        this.exitApplication = exitApplication;
    }

    @GetMapping
    public ResponseEntity closeApp() {
        exitApplication.exit();
        return new ResponseEntity(HttpStatus.OK);
    }

}

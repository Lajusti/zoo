package lajusticia.alejandro.zoo;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ExitApplication {

    private final ConfigurableApplicationContext ctx;

    public ExitApplication(final ConfigurableApplicationContext ctx) {
        this.ctx = ctx;
    }

    public void exit() {
        SpringApplication.exit(ctx, new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                return 0;
            }
        });
    }

}

package lajusticia.alejandro.zoo.animal.domain.identifier;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class AnimalIdentifier {

    private final String id;

    public AnimalIdentifier() {
        this.id = UUID.randomUUID().toString();
    }

    public AnimalIdentifier(final String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AnimalIdentifier animalIdentifier = (AnimalIdentifier) object;
        return id.equals(animalIdentifier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }

}

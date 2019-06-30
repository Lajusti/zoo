package lajusticia.alejandro.zoo.animal.domain.type;

public enum DogType {

    HUNTING_DOG ("Hunting dog"),
    WORKING_DOG ("Working dog"),
    SPORT_DOG ("Sport dog");

    private final String name;

    private DogType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}

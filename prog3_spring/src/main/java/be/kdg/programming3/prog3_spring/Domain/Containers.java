package be.kdg.programming3.prog3_spring.Domain;

public enum Containers {
    BOTTLE("bottle"), CAN("can"), KEG("keg");

    private final String container;

    // private enum constructor
    private Containers(String container) {
        this.container = container;
    }

    public String getType() {
        return container;
    }
}

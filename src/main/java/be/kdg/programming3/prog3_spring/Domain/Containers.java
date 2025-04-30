package be.kdg.programming3.prog3_spring.Domain;

public enum Containers {
    BOTTLE("bottle"), CAN("can"), KEG("keg");

    private final String name;
    Containers(String name) { this.name = name; }

    public String getName() {return name;}
}

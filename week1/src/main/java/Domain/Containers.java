package Domain;

public enum Containers {
    BOTTLE("bottle"), CAN("can"), KEG("keg");

    private final String container;

    // private enum constructor
    private Containers(String container) {
        this.container = container;
    }

    public String getContainer() {
        return container;
    }
}

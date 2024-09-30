package Domain;

public enum Quantities {
    NIP("7oz"), STUBBY("12oz"), LONGNECK("12oz"), BELGIAN("375ml"), BRITISH("500ml"), BOMBER("650ml"), LARGE_FORMAT("750ml"), CAGUAMA("940ml"), HOWLER("32oz"),
    TALLBOY("16oz"), STOVEPIPE("19,2oz"), CROWLER("32oz");

    private final String size;

    // private enum constructor
    private Quantities(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

}

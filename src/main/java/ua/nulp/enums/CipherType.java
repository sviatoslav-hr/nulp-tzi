package ua.nulp.enums;

public enum CipherType {
    NONE("Select cipher"), CAESAR("Caesar cipher");

    private final String name;

    public String getName() {
        return name;
    }

    CipherType(String name) {
        this.name = name;
    }
}
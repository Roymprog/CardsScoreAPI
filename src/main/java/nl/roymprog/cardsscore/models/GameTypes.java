package nl.roymprog.cardsscore.models;

public enum GameTypes {
    CHINEES_POEPEN("chinees poepen");

    private final String name;

    GameTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

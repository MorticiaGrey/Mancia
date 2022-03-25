package morticia.mancia.Alignment;

public enum ManciaAlignment {
    NECRO("Necromancer"),
    VITO("Vitomancer"),
    NEUTRAL("None");

    public final String label;

    ManciaAlignment(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}

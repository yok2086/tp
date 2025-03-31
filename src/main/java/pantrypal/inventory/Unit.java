package pantrypal.inventory;

public enum Unit {
    GRAM("g"),
    KILOGRAM("kg"),
    TEASPOON("tsp"),
    TABLESPOON("tbsp"),
    CUP("cup"),
    OUNCE("oz"),
    LITER("L"),
    MILLILITER("mL"),
    POUND("lb");
    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return unit;
    }

    public static Unit parseUnit(String text) {
        for (Unit unit : Unit.values()) {
            if (unit.unit.equalsIgnoreCase(text) || unit.name().equalsIgnoreCase(text)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid unit: " + text + "\nType unitList for list of valid units");
    }
}

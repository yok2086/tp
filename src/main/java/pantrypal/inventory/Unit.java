package pantrypal.inventory;

public enum Unit {
    GRAM("g", 1.0),
    KILOGRAM("kg", 1000.0),
    TEASPOON("tsp", 4.2),
    TABLESPOON("tbsp", 14.3),
    CUP("cup", 240.0),
    OUNCE("oz", 28.35),
    LITER("L", 1000.0),
    MILLILITER("mL", 1.0),
    POUND("lb", 453.59);

    private final String unit;
    private final double conversion; // NEW

    Unit(String unit, double conversion) {
        this.unit = unit;
        this.conversion = conversion;
    }

    public String getUnit() {
        return unit;
    }

    public double getConversion() {
        return conversion;
    }

    public static double convert(double quantity, Unit from, Unit to) {
        return (quantity * from.getConversion()) / to.getConversion();
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
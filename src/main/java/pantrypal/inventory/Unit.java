package pantrypal.inventory;

public enum Unit {
    GRAM("g", 1.0),
    KILOGRAM("kg", 1000.0),
    MILLILITER("mL", 1.0),
    CUP("cup", 240.0),
    LITER("L", 1000.0),
    OUNCE("oz", 28.35),
    POUND("lb", 453.59),
    MILLIGRAM("mg", 0.001),

    PIECE("piece", -1.0),
    EGG_WHITE("egg_white", -1.0),
    EGG_YOLK("egg_yolk", -1.0),
    PINCH("pinch", -1.0),
    SPLASH("splash", -1.0),
    BUNCH("bunch", -1.0),
    SLICES("slices", -1.0),
    CLOVE("clove", -1.0),
    PINT("pt", -1.0),
    QUART("qt", -1.0),
    GALLON("gal", -1.0),
    FLUID_OUNCE("fl_oz", -1.0),
    CUBIC_CENTIMETER("cc", -1.0),
    CUBIC_INCH("in³", -1.0),
    CUBIC_FOOT("ft³", -1.0),
    SQUARE_INCH("in²", -1.0),
    SQUARE_FOOT("ft²", -1.0),
    SQUARE_METER("m²", -1.0),
    SQUARE_CENTIMETER("cm²", -1.0),
    LITER_PER_MINUTE("L/min", -1.0),
    KILOCALORIE("kcal", -1.0),
    CALORIE("cal", -1.0);

    private final String unit;
    private final double conversion;

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
        boolean validConversion = true;

        if ((from == KILOGRAM || from == GRAM || from == MILLIGRAM) &&
                (to == KILOGRAM || to == GRAM || to == MILLIGRAM)) {
            // Allow conversion between KG, G, and MG
            validConversion = true;
        } else if (from == KILOGRAM && (to != GRAM && to != MILLIGRAM)) {
            validConversion = false;
        } else if (from == GRAM && (to != KILOGRAM && to != MILLIGRAM)) {
            validConversion = false;
        } else if (from == MILLIGRAM && (to != GRAM && to != KILOGRAM)) {
            validConversion = false;
        } else if ((from == CUP || from == OUNCE || from == POUND) &&
                (to == CUP || to == OUNCE || to == POUND)) {
            // Allow conversion between CUP, OUNCE, and POUND
            validConversion = true;
        } else if (from == MILLILITER && to != LITER) {
            validConversion = false;
        } else if (from == LITER && to != MILLILITER) {
            validConversion = false;
        }
        if (validConversion) {
            return (quantity * from.getConversion()) / to.getConversion();
        } else {
            System.out.println("You can't convert from " + from + " to " + to + ".");
            return -1.0;
        }
    }

    @Override
    public String toString() {
        return unit;
    }

    public static Unit parseUnit(String text) {
        Unit result = null;
        boolean found = false;

        for (Unit unit : Unit.values()) {
            if (unit.unit.equalsIgnoreCase(text) || unit.name().equalsIgnoreCase(text)) {
                result = unit;
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Invalid unit: " + text + "\nType unitList for list of valid units");
        }

        return result;
    }
}

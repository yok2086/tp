package pantrypal.inventory;

/**
 * Enum representing various units of measurement for ingredients.
 * Includes common units like grams, kilograms, milliliters, and others.
 * Provides methods for unit conversion and parsing.
 */
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

    /**
     * Constructor for the Unit enum.
     *
     * @param unit       The unit abbreviation (e.g., "g", "kg").
     * @param conversion The conversion factor for the unit (relative to grams or milliliters).
     */
    Unit(String unit, double conversion) {
        this.unit = unit;
        this.conversion = conversion;
    }

    /**
     * Gets the unit abbreviation (e.g., "g", "kg").
     *
     * @return The unit abbreviation.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Gets the conversion factor for the unit.
     *
     * @return The conversion factor.
     */
    public double getConversion() {
        return conversion;
    }

    /**
     * Converts the given quantity from one unit to another.
     * Only allows conversions between certain compatible units (e.g., grams to kilograms).
     *
     * @param quantity The quantity to convert.
     * @param from     The unit to convert from.
     * @param to       The unit to convert to.
     * @return The converted quantity or -1 if the conversion is not possible.
     */
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

    /**
     * Returns the string representation of the unit.
     *
     * @return The unit abbreviation.
     */
    @Override
    public String toString() {
        return unit;
    }

    /**
     * Parses a unit from a string. The string can be either the unit abbreviation or the enum name.
     *
     * @param text The unit string to parse.
     * @return The corresponding Unit enum.
     * @throws IllegalArgumentException If the unit is invalid.
     */
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

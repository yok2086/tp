package pantrypal.inventory;

import java.util.Objects;

/**
 * The Ingredient class represents an ingredient in the inventory with its name, quantity, unit of measurement,
 * and category. It provides methods to get and set these properties, and overrides the `toString` and `hashCode`
 * methods to support proper string representation and uniqueness checks based on the ingredient name.
 */
public class Ingredient {
    protected String name;
    protected double quantity;
    protected Unit unit;
    protected Category category;

    /**
     * Constructor to create a new Ingredient object.
     *
     * @param name The name of the ingredient (e.g., "Sugar").
     * @param quantity The amount of the ingredient (e.g., 500 grams).
     * @param unit The unit of measurement for the ingredient (e.g., grams, kilograms).
     * @param category The category the ingredient belongs to (e.g., "Baking", "Vegetables").
     */
    public Ingredient(String name, double quantity, Unit unit, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
    }

    /**
     * Gets the name of the ingredient.
     *
     * @return The name of the ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the quantity of the ingredient.
     *
     * @return The quantity of the ingredient.
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Gets the unit of measurement for the ingredient.
     *
     * @return The unit of the ingredient.
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the quantity of the ingredient.
     *
     * @param quantity The new quantity to set.
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the unit of measurement for the ingredient.
     *
     * @param unit The new unit to set.
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Sets the name of the ingredient.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the category of the ingredient.
     *
     * @return The category of the ingredient.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category of the ingredient.
     *
     * @param category The new category to set.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Returns a string representation of the Ingredient object.
     *
     * @return A string that contains the name, quantity, unit, and category of the ingredient.
     */
    @Override
    public String toString() {
        return name + " " + quantity + " " + unit + " " + category;
    }

    /**
     * Overrides the hashCode method to return a hash code based on the name of the ingredient.
     * This ensures that the Ingredient object can be correctly identified in collections like HashMap.
     *
     * @return The hash code for this ingredient based on its name.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

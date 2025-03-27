package pantrypal.inventory;

import java.util.Objects;

public class Ingredient {
    protected String name;
    protected double quantity;
    protected Unit unit;

    //Change the access modifier to public to be used by RecipeManager
    public Ingredient(String name, double quantity, Unit unit) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be empty.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Ingredient quantity cannot be negative.");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setQuantity(double quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    public void setUnit(Unit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
        this.unit = unit;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be empty.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + quantity + " " + unit + " ";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}

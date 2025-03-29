package pantrypal.inventory;

import java.time.LocalDate;
import java.util.Objects;

public class Ingredient {
    protected String name;
    protected double quantity;
    protected Unit unit;
    protected LocalDate expiryDate;

    //Change the access modifier to public to be used by RecipeManager
    public Ingredient(String name, double quantity, Unit unit, LocalDate expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        setExpiryDate(expiryDate);
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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        if (expiryDate != null && expiryDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past.");
        }
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return name + " " + quantity + " " + unit +
                (expiryDate != null ? " (Expires: " + expiryDate + ")" : "");
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate);
    }
}

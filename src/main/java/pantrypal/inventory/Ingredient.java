package pantrypal.inventory;

import java.util.Objects;

public class Ingredient {
    protected String name;
    protected double quantity;
    protected Unit unit;

    //Change the access modifier to public to be used by RecipeManager
    public Ingredient(String name, double quantity, Unit unit) {
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
        this.quantity = quantity;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

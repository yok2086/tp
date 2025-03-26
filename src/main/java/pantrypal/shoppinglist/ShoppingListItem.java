package pantrypal.shoppinglist;

import pantrypal.inventory.Unit;

public class ShoppingListItem {
    private String ingredientName;
    private double quantity;
    private Unit unit;

    public ShoppingListItem(String ingredientName, double quantity, Unit unit) {
        assert ingredientName != null : "Ingredient name must not be null.";
        assert unit != null : "Unit must not be null.";
        assert quantity >= 0 : "Quantity must be non-negative.";
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Copy constructor
    public ShoppingListItem(ShoppingListItem newShoppingListItem) {
        this.ingredientName = newShoppingListItem.ingredientName;
        this.quantity = newShoppingListItem.quantity;
        this.unit = newShoppingListItem.unit;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        assert ingredientName != null : "Ingredient name must not be null.";
        this.ingredientName = ingredientName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        assert quantity >= 0 : "Quantity must be non-negative.";
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        assert unit != null : "Unit must not be null.";
        this.unit = unit;
    }

    @Override
    public String toString() {
        return ingredientName + ": " + quantity + " " + unit;
    }
}

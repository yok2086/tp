package pantrypal.shoppinglist;

import pantrypal.inventory.Unit;

public class ShoppingListItem {
    private String ingredientName;
    private double quantity;
    private Unit unit;
    private boolean purchased;

    public ShoppingListItem(String ingredientName, double quantity, Unit unit) {
        assert ingredientName != null : "Ingredient name must not be null.";
        assert unit != null : "Unit must not be null.";
        assert quantity >= 0 : "Quantity must be non-negative.";
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
        this.purchased = false; // Default: not purchased
        this.category = category;
    }

    // Copy constructor
    public ShoppingListItem(ShoppingListItem newShoppingListItem) {
        this.ingredientName = newShoppingListItem.ingredientName;
        this.quantity = newShoppingListItem.quantity;
        this.unit = newShoppingListItem.unit;
        this.purchased = newShoppingListItem.purchased;
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

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public String toString() {
        // Format quantity: if it's a whole number, print without decimal.
        String quantityStr = (quantity % 1 == 0) ? String.format("%d", (long) quantity)
                : String.valueOf(quantity);
        return ingredientName + ": " + quantityStr + " " + unit + (purchased ? " (Purchased)" : "");
    }

    public Category getCategory() {
        return category;
    }
}

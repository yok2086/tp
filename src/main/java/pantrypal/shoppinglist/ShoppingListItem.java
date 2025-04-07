package pantrypal.shoppinglist;

import pantrypal.inventory.Unit;

/**
 * Represents a single item in a shopping list.
 * Each item contains an ingredient name, quantity, unit, and a purchased status.
 */
public class ShoppingListItem {
    private String ingredientName;
    private double quantity;
    private Unit unit;
    private boolean purchased;

    /**
     * Constructs a new ShoppingListItem with the specified parameters.
     *
     * @param ingredientName The name of the ingredient
     * @param quantity The quantity of the ingredient
     * @param unit The unit of measurement for the quantity
     * @throws IllegalArgumentException if ingredientName is null or empty,
     *         quantity is negative, or unit is null
     */
    public ShoppingListItem(String ingredientName, double quantity, Unit unit) {
        assert ingredientName != null : "Ingredient name must not be null.";
        assert unit != null : "Unit must not be null.";
        assert quantity >= 0 : "Quantity must be non-negative.";
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
        this.purchased = false; // Default: not purchased
    }

    /**
     * Constructs a new ShoppingListItem as a copy of an existing item.
     *
     * @param newShoppingListItem The ShoppingListItem to copy
     * @throws IllegalArgumentException if newShoppingListItem is null
     */
    public ShoppingListItem(ShoppingListItem newShoppingListItem) {
        if (newShoppingListItem == null) {
            throw new IllegalArgumentException("ShoppingListItem to copy cannot be null");
        }
        this.ingredientName = newShoppingListItem.ingredientName;
        this.quantity = newShoppingListItem.quantity;
        this.unit = newShoppingListItem.unit;
        this.purchased = newShoppingListItem.purchased;
    }

    /**
     * Returns the name of the ingredient.
     *
     * @return The ingredient name
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Sets the name of the ingredient.
     *
     * @param ingredientName The new ingredient name
     */
    public void setIngredientName(String ingredientName) {
        assert ingredientName != null : "Ingredient name must not be null.";
        this.ingredientName = ingredientName;
    }

    /**
     * Returns the quantity of the ingredient.
     *
     * @return The quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ingredient.
     *
     * @param quantity The new quantity
     */
    public void setQuantity(double quantity) {
        assert quantity >= 0 : "Quantity must be non-negative.";
        this.quantity = quantity;
    }

    /**
     * Returns the unit of measurement.
     *
     * @return The unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the unit of measurement.
     *
     * @param unit The new unit
     */
    public void setUnit(Unit unit) {
        assert unit != null : "Unit must not be null.";
        this.unit = unit;
    }

    /**
     * Returns whether the item has been purchased.
     *
     * @return true if the item has been purchased, false otherwise
     */
    public boolean isPurchased() {
        return purchased;
    }

    /**
     * Sets the purchased status of the item.
     *
     * @param purchased The new purchased status
     */
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

}

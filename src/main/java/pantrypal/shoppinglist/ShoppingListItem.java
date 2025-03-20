package pantrypal.shoppinglist;

public class ShoppingListItem {
    private String ingredientName;
    private double quantity;
    private String unit;

    public ShoppingListItem(String ingredientName, double quantity, String unit) {
        assert ingredientName != null : "Ingredient name must not be null.";
        assert unit != null : "Unit must not be null.";
        assert quantity >= 0 : "Quantity must be non-negative.";
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        assert unit != null : "Unit must not be null.";
        this.unit = unit;
    }

    @Override
    public String toString() {
        return ingredientName + ": " + quantity + " " + unit;
    }
}

package pantrypal.shoppinglist;

public class ShoppingListItem {
    private String ingredientName;
    private int quantity;
    private String unit;

    public ShoppingListItem(String ingredientName, int quantity, String unit) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return ingredientName + ": " + quantity + " " + unit;
    }
}

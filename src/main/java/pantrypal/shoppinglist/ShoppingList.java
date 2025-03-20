package pantrypal.shoppinglist;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private List<ShoppingListItem> items;

    public ShoppingList() {
        items = new ArrayList<>();
        assert items != null : "Items list should be initialized.";
    }

    // Add a new item to the shopping list.
    public void addItem(ShoppingListItem item) {
        assert item != null : "ShoppingListItem cannot be null.";
        items.add(item);
    }

    // Get the list of shopping items.
    public List<ShoppingListItem> getItems() {
        assert items != null : "Items list should never be null.";
        return items;
    }

    // Remove an item by ingredient name.
    public boolean removeItem(String ingredientName) {
        assert ingredientName != null : "Ingredient name cannot be null.";
        return items.removeIf(item -> item.getIngredientName().equalsIgnoreCase(ingredientName));
    }

    // Update an existing item's quantity and unit.
    public boolean updateItem(String ingredientName, double newQuantity, String newUnit) {
        assert ingredientName != null : "Ingredient name cannot be null.";
        assert newUnit != null : "Unit cannot be null.";
        assert newQuantity >= 0 : "Quantity should be non-negative.";
        for (ShoppingListItem item : items) {
            if (item.getIngredientName().equalsIgnoreCase(ingredientName)) {
                item.setQuantity(newQuantity);
                item.setUnit(newUnit);
                return true;
            }
        }
        return false;
    }

    // Display the shopping list.
    public void displayList() {
        assert items != null : "Items list should not be null when displaying.";
        if (items.isEmpty()) {
            System.out.println("Shopping list is empty.");
        } else {
            System.out.println("Shopping List:");
            for (ShoppingListItem item : items) {
                System.out.println("- " + item);
            }
        }
    }

    public void copyFrom(ShoppingList newShoppingList) {
        if (newShoppingList != null) {
            // Clear existing items
            this.items.clear();

            // Copy all items from the other list
            for (ShoppingListItem item : newShoppingList.getItems()) {
                this.items.add(new ShoppingListItem(item)); // Assuming ShoppingListItem has a copy constructor
            }
        }
    }

}

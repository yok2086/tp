package pantrypal.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import pantrypal.inventory.Category;
import pantrypal.inventory.Unit;

public class ShoppingList {
    private List<ShoppingListItem> items;

    public ShoppingList() {
        items = new ArrayList<>();
        assert items != null : "Items list should be initialized.";
    }

    // Add a new item to the shopping list.
    public boolean addItem(ShoppingListItem item) {
        assert item != null : "ShoppingListItem cannot be null.";
        // Check for an existing item with the same name.
        for (ShoppingListItem existingItem : items) {
            if (existingItem.getIngredientName().equalsIgnoreCase(item.getIngredientName())) {
                return false;
            }
        }
        items.add(item);
        return true;
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
    public boolean updateItem(String ingredientName, double newQuantity, Unit newUnit) {
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

    // Edit an item by its index in the list.
    public boolean editItem(int index, String newIngredientName, double newQuantity, Unit newUnit) {
        assert newIngredientName != null : "Ingredient name cannot be null.";
        assert newUnit != null : "Unit cannot be null.";
        assert newQuantity >= 0 : "Quantity must be non-negative.";
        index = index - 1;
        if (index < 0 || index >= items.size()) {
            return false;
        }
        ShoppingListItem item = items.get(index);
        item.setIngredientName(newIngredientName);
        item.setQuantity(newQuantity);
        item.setUnit(newUnit);
        return true;
    }

    // Display the shopping list.
    public void displayList() {
        assert items != null : "Items list should not be null when displaying.";
        if (items.isEmpty()) {
            System.out.println("Shopping list is empty.");
        } else {
            System.out.println("Shopping List:");
            for (int i = 1; i <= items.size(); i++) {
                System.out.println(i + ": " + items.get(i-1));
            }
        }
    }

    // Mark an item as purchased by its ingredient name.
    public boolean markItemAsPurchased(String ingredientName) {
        assert ingredientName != null : "Ingredient name cannot be null.";
        for (ShoppingListItem item : items) {
            if (item.getIngredientName().equalsIgnoreCase(ingredientName)) {
                item.setPurchased(true);
                return true;
            }
        }
        return false;
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

    public void copyList(ShoppingList newShoppingList) {
        if (newShoppingList != null) {
            this.items.clear();
            for (ShoppingListItem item : newShoppingList.getItems()) {
                this.items.add(new ShoppingListItem(item));
            }
        }
    }

}

package pantrypal.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import pantrypal.general.control.Ui;
import pantrypal.inventory.Unit;

/**
 * Manages a collection of shopping list items.
 * Provides functionality to add, remove, update, and edit items in the shopping list.
 */
public class ShoppingList {
    private List<ShoppingListItem> items;

    /**
     * Constructs an empty shopping list.
     */
    public ShoppingList() {
        items = new ArrayList<>();
        assert items != null : "Items list should be initialized.";
    }

    /**
     * Adds a new item to the shopping list.
     * If an item with the same name already exists, their quantities are combined.
     *
     * @param item The item to add
     * @return true if the item was added successfully, false otherwise
     */
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

    /**
     * Returns all items in the shopping list.
     *
     * @return A list of all shopping list items
     */
    public List<ShoppingListItem> getItems() {
        assert items != null : "Items list should never be null.";
        return items;
    }

    /**
     * Removes an item from the shopping list by name.
     *
     * @param ingredientName The name of the ingredient to remove
     * @return true if the item was found and removed, false otherwise
     * @throws IllegalArgumentException if ingredientName is null or empty
     */
    public boolean removeItem(String ingredientName) {
        assert ingredientName != null : "Ingredient name cannot be null.";
        return items.removeIf(item -> item.getIngredientName().equalsIgnoreCase(ingredientName));
    }

    /**
     * Updates an existing item in the shopping list.
     *
     * @param ingredientName The name of the ingredient to update
     * @param newQuantity The new quantity
     * @param newUnit The new unit
     * @return true if the item was found and updated, false otherwise
     */
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

    /**
     * Edits an item in the shopping list by index.
     *
     * @param index The index of the item to edit
     * @param newIngredientName The new ingredient name
     * @param newQuantity The new quantity
     * @param newUnit The new unit
     */
    public boolean editItem(int index, String newIngredientName, double newQuantity, Unit newUnit) {
        int count = 0;
        for (ShoppingListItem existingItem : items) {
            if (existingItem.getIngredientName().equalsIgnoreCase(newIngredientName)) {
                count = count + 1;
            }
        }
        if(items.get(index).getIngredientName() != newIngredientName && count >= 1){
            return false;
        }

        ShoppingListItem item = items.get(index);
        item.setIngredientName(newIngredientName);
        item.setQuantity(newQuantity);
        item.setUnit(newUnit);

        return true;
    }

    /**
     * Displays the shopping list.
     */
    public void displayList() {
        assert items != null : "Items list should not be null when displaying.";
        if (items.isEmpty()) {
            System.out.println("No items need to be added to shopping list");
            Ui.printLine();
        } else {
            System.out.println("Shopping List:");
            for (int i = 1; i <= items.size(); i++) {
                System.out.println(i + ": " + items.get(i-1));
            }
            Ui.printLine();
        }
    }

    /**
     * Marks an item as purchased by name.
     *
     * @param ingredientName The name of the ingredient to mark as purchased
     * @return true if the item was found and marked, false otherwise
     * @throws IllegalArgumentException if ingredientName is null or empty
     */
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

    /**
     * Copies all items from another shopping list into this one.
     * Existing items with the same name will have their quantities combined.
     *
     * @param newShoppingList The shopping list to copy from
     * @throws IllegalArgumentException if newShoppingList is null
     */
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

    /**
     * Replaces all items in this shopping list with items from another shopping list.
     *
     * @param newShoppingList The shopping list to copy from
     * @throws IllegalArgumentException if newShoppingList is null
     */
    public void copyList(ShoppingList newShoppingList) {
        if (newShoppingList != null) {
            this.items.clear();
            for (ShoppingListItem item : newShoppingList.getItems()) {
                this.items.add(new ShoppingListItem(item));
            }
        }
    }
}

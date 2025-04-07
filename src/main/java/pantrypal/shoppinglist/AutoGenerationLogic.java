// AutoGenerationLogic.java
package pantrypal.shoppinglist;

import java.util.Map;

import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;

/**
 * Handles the automatic generation of shopping lists based on low stock alerts
 * from the ingredient inventory.
 */
public class AutoGenerationLogic {
    private ShoppingList shoppingList;
    private IngredientInventory inventory;

    /**
     * Constructs a new AutoGenerationLogic with the specified shopping list and inventory.
     *
     * @param shoppingList The shopping list to update
     * @param inventory The ingredient inventory to monitor
     * @throws IllegalArgumentException if shoppingList or inventory is null
     */
    public AutoGenerationLogic(ShoppingList shoppingList, IngredientInventory inventory) {
        if (shoppingList == null) {
            throw new IllegalArgumentException("Shopping list cannot be null");
        }
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory cannot be null");
        }
        this.shoppingList = shoppingList;
        this.inventory = inventory;
    }

    /**
     * Generates a shopping list for ingredients that are below their low stock threshold.
     * For each ingredient in the inventory:
     * 1. Checks if the current quantity is below the low stock threshold
     * 2. If below threshold, adds the difference to the shopping list
     *
     * @return A new ShoppingList containing items that need to be restocked
     */
    public ShoppingList generateLowStockShoppingList() {
        ShoppingList shoppingList = new ShoppingList();
        Map<String, Double> alerts = inventory.getLowStockAlerts();
        Map<String, Ingredient> ingredients = inventory.getInventory();

        for (Map.Entry<String, Double> entry : alerts.entrySet()) {
            String ingredientName = entry.getKey();
            double threshold = entry.getValue();
            Ingredient ingredient = ingredients.get(ingredientName);

            if (ingredient != null && ingredient.getQuantity() < threshold) {
                double required = threshold - ingredient.getQuantity();
                shoppingList.addItem(new ShoppingListItem(
                    ingredientName,
                    required,
                    ingredient.getUnit()
                ));
            }
        }
        return shoppingList;
    }

    // Generate shopping list items for ingredients that are below their low stock alert threshold.
    public void generateShoppingList() {
        Map<String, Double> alerts = inventory.getLowStockAlerts();
        Map<String, Ingredient> ingredients = inventory.getInventory();

        for (Map.Entry<String, Double> entry : alerts.entrySet()) {
            String ingredientName = entry.getKey();
            double threshold = entry.getValue();

            // Retrieve the ingredient details from the inventory
            Ingredient ingredient = ingredients.get(ingredientName);
            if (ingredient != null && ingredient.getQuantity() <= threshold) {
                double required = threshold - ingredient.getQuantity();

                // Update existing item if present; if not, add a new item.
                boolean updated = shoppingList.updateItem(ingredientName, required, ingredient.getUnit());
                if (!updated) {
                    shoppingList.addItem(new ShoppingListItem(ingredientName, required, ingredient.getUnit()));
                }
            }
        }
    }
}

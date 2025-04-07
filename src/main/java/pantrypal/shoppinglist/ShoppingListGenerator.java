package pantrypal.shoppinglist;

import java.util.Map;
import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;

/**
 * Generates a shopping list based on the difference between required ingredients
 * and available ingredients in the inventory.
 */
public class ShoppingListGenerator {
    private ShoppingList shoppingList;
    private IngredientInventory inventory;

    /**
     * Constructs a new ShoppingListGenerator with the specified inventory.
     *
     * @param inventory The ingredient inventory to use for generation
     */
    public ShoppingListGenerator(IngredientInventory inventory) {
        this.shoppingList = new ShoppingList();
        this.inventory = inventory;
    }

    // Generate shopping list items for ingredients that are below their low stock alert threshold.
    public ShoppingList generateShoppingList() {
        Map<String, Double> alerts = inventory.getLowStockAlerts();
        Map<String, Ingredient> ingredients = inventory.getInventory();

        for (Map.Entry<String, Double> entry : alerts.entrySet()) {
            String ingredientName = entry.getKey();
            double threshold = entry.getValue();

            // Retrieve the ingredient details from the inventory
            Ingredient ingredient = ingredients.get(ingredientName);
            if (ingredient != null && ingredient.getQuantity() < threshold) {
                double required = threshold - ingredient.getQuantity();

                // Update existing item if present; if not, add a new item.
                boolean updated = shoppingList.updateItem(ingredientName, required, ingredient.getUnit());
                if (!updated) {
                    shoppingList.addItem(new ShoppingListItem(ingredientName, required, ingredient.getUnit()));
                }
            }
        }
        return shoppingList;
    }
}

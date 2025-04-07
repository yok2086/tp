package pantrypal.inventory;

import pantrypal.general.control.Ui;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages an inventory of ingredients, including adding, removing, updating quantities,
 * checking stock levels, setting low stock alerts, and viewing ingredients by category.
 */
public class IngredientInventory {
    /**
     * Stores ingredients by their name.
     */
    private Map<String, Ingredient> inventory;
    /**
     * Stores low stock alert thresholds by ingredient name.
     */
    private Map<String, Double> lowStockAlerts;

    /**
     * Constructor to initialize ingredient inventory and alert map.
     */
    public IngredientInventory() {
        inventory = new HashMap<>();
        lowStockAlerts = new HashMap<>();
    }

    /**
     * Adds a new ingredient to the inventory.
     *
     * @param name     Name of the ingredient.
     * @param quantity Initial quantity.
     * @param unit     Unit of measurement.
     * @param category Ingredient category.
     */
    public void addNewIngredient(String name, double quantity, Unit unit, Category category) {
        assert name != null && !name.isEmpty() : "Ingredient name is null or empty";
        assert quantity > 0 : "Quantity must be positive";
        assert unit != null : "Unit is null";
        assert category != null : "Category is null";

        try {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Ingredient name cannot be null or empty.");
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive.");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null.");
            }
            if (category == null) {
                throw new IllegalArgumentException("Category cannot be null.");
            }
            if (inventory.containsKey(name)) {
                System.out.println("This ingredient already exists.");
                throw new IllegalArgumentException("Ingredient '" + name + "' already exists.");
            }
            inventory.put(name, new Ingredient(name, quantity, unit, category));
        } catch (IllegalArgumentException e) {
            Ui.showMessage(e.getMessage());
        }
    }


    /**
     * Increases the quantity of an existing ingredient.
     *
     * @param name     Name of the ingredient.
     * @param quantity Quantity to increase.
     */
    public void increaseQuantity(String name, double quantity) {
        if (quantity < 0) {
            Ui.showMessage("Error: Quantity to increase must be positive.");
            return;
        }
        Ingredient ingredient = inventory.get(name);
        if (ingredient != null) {
            ingredient.quantity += quantity;
            Ui.showMessage("Increased " + name + " by " + quantity);
        } else {
            Ui.showMessage("Ingredient not found");
        }
    }

    /**
     * Decreases the quantity of an existing ingredient.
     *
     * @param name     Name of the ingredient.
     * @param quantity Quantity to decrease.
     */
    public void decreaseQuantity(String name, double quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity to decrease cannot be negative.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or empty.");
        }

        Ingredient ingredient = inventory.get(name);
        if (ingredient != null) {
            if (ingredient.quantity >= quantity) {
                ingredient.quantity -= quantity;
            } else {
                throw new IllegalArgumentException("Not enough " + name.toUpperCase() + " in stock.");
            }
        } else {
            throw new IllegalArgumentException("Ingredient not found");
        }
    }

    /**
     * Sets a low stock alert threshold for a specific ingredient.
     *
     * @param name      Ingredient name.
     * @param threshold Minimum quantity before alert.
     */
    public void setAlert(String name, double threshold) {
        lowStockAlerts.put(name, threshold);
    }

    /**
     * Retrieves all low stock alert thresholds.
     *
     * @return Map of ingredient names to their alert thresholds.
     */
    public Map<String, Double> getLowStockAlerts() {
        return lowStockAlerts;
    }

    /**
     * Retrieves the entire ingredient inventory.
     *
     * @return Map of ingredient names to Ingredient objects.
     */
    public Map<String, Ingredient> getInventory() {
        return inventory;
    }

    /**
     * Prints the current inventory to the console.
     */
    public void checkStock() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            inventory.forEach((name, ingredient) -> {
                System.out.println(name + ": " + ingredient.quantity + " " +
                        ingredient.unit + " " + ingredient.category);
            });
        }
    }

    /**
     * Checks if a specific quantity of an ingredient is in stock.
     *
     * @param name     Ingredient name.
     * @param quantity Quantity needed.
     * @param unit     Unit of the requested quantity.
     * @return true if in inventory with sufficient quantity, else false.
     */
    public boolean isInInventory(String name, double quantity, Unit unit) {
        Ingredient ingredient = inventory.get(name);

        if (ingredient != null
                && ingredient.getQuantity() >= quantity
                && ingredient.getUnit() == unit) {
            return true;
        }
        return false;
    }

    /**
     * Prints ingredients that are below their low stock thresholds.
     */
    public void viewLowStock() {
        boolean found = false;
        for (Map.Entry<String, Double> alert : lowStockAlerts.entrySet()) {
            Ingredient ingredient = inventory.get(alert.getKey());
            if (ingredient != null && ingredient.quantity < alert.getValue()) {
                System.out.println("Low stock: " + ingredient.name + " (" + ingredient.quantity + " " +
                        ingredient.unit + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No low stock ingredients.");
        }
    }

    /**
     * Deletes an ingredient from the inventory and removes its alert if set.
     *
     * @param name Ingredient name to delete.
     */
    public void deleteIngredient(String name) {
        if (inventory.remove(name) != null) {
            lowStockAlerts.remove(name);
            System.out.println("Deleted " + name + " from inventory.");
        } else {
            System.out.println("Ingredient not found.");
        }
    }

    /**
     * Converts an ingredient's quantity from its current unit to another.
     *
     * @param name Ingredient name.
     * @param targetUnit Unit to convert to.
     */
    public void convertIngredient(String name, Unit targetUnit) {
        try {
            Ingredient ingredient = inventory.get(name);
            if (ingredient == null) {
                throw new IllegalArgumentException("Ingredient not found.");
            }
            Unit currentUnit = ingredient.getUnit();
            double convertedQuantity = Unit.convert(ingredient.getQuantity(), currentUnit, targetUnit);
            if (convertedQuantity == -1.0) {
                throw new IllegalArgumentException("Invalid conversion from " + currentUnit + " to " + targetUnit
                        + " for " + name);
            }
            ingredient.setQuantity(convertedQuantity);
            ingredient.setUnit(targetUnit);
            System.out.println(name + " converted to " + convertedQuantity + " " + targetUnit);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns all ingredients in a specified category.
     *
     * @param category Category to filter by.
     * @return A string listing ingredients in that category.
     */
    public String viewIngredientsByCategory(Category category) {
        boolean found = false;
        StringBuilder result = new StringBuilder(); // Use StringBuilder to collect output

        for (Ingredient ingredient : inventory.values()) {
            if (ingredient.getCategory() == category) {
                result.append(ingredient).append("\n"); // Append ingredient to result
                found = true;
            }
        }
        if (!found) {
            result.append("No ingredients found in category: ").append(category);
        }
        return result.toString(); // Return the result as a string
    }

    /**
     * Validates if a category string is valid.
     *
     * @param category Category name as string.
     */
    public void validateIngredientCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null.\nType categoryList for a list of valid " +
                    "categories.");
        }

        try {
            Category parsedCategory = Category.parseCategory(category);
            System.out.println("Valid category: " + parsedCategory);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category: " + category +
                    "\nType categoryList for a list of valid categories.");
        }
    }
}

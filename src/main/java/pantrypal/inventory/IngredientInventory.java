package pantrypal.inventory;

import pantrypal.general.control.Ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            throw new IllegalArgumentException("Quantity to increase must be positive.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or empty.");
        }

        Ingredient ingredient = inventory.get(name);
        if (ingredient != null) {
            BigDecimal ingredientQuantity = new BigDecimal(Double.toString(ingredient.quantity));
            BigDecimal quantityToIncrease = new BigDecimal(Double.toString(quantity));
            BigDecimal newQuantity = ingredientQuantity.add(quantityToIncrease);
            newQuantity = newQuantity.setScale(8, RoundingMode.HALF_UP);
            ingredient.quantity = newQuantity.doubleValue();
            BigDecimal increaseAmount = new BigDecimal(Double.toString(quantity));
            String increaseAmountString = increaseAmount.stripTrailingZeros().toPlainString();

            Ui.showMessage("Increased " + name + " by " + increaseAmountString);
        } else {
            throw new IllegalArgumentException("Ingredient not found");
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
            BigDecimal ingredientQuantity = new BigDecimal(Double.toString(ingredient.quantity));
            BigDecimal quantityToDecrease = new BigDecimal(Double.toString(quantity));

            BigDecimal newQuantity = ingredientQuantity.subtract(quantityToDecrease);

            if (newQuantity.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Not enough " + name.toUpperCase() + " in stock.");
            }
            newQuantity = newQuantity.setScale(8, RoundingMode.HALF_UP);
            ingredient.quantity = newQuantity.doubleValue();

            BigDecimal decreaseAmount = new BigDecimal(Double.toString(quantity));
            String decreaseAmountString = decreaseAmount.stripTrailingZeros().toPlainString();  

            Ui.showMessage("Decreased " + name + " by " + decreaseAmountString);
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
        System.out.println("____________________________________________________________");
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
        System.out.println("______________________________________________________________");
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
        boolean firstMatch = true;
        StringBuilder result = new StringBuilder();

        for (Ingredient ingredient : inventory.values()) {
            if (ingredient.getCategory() == category) {
                if (firstMatch) {
                    result.append("Displaying ingredients found in this category:\n");
                    firstMatch = false;
                } else {
                    result.append("\n"); // Add newline before subsequent ingredients
                }
                result.append(ingredient);
                found = true;
            }
        }

        if (!found) {
            result.append("No ingredients found in category: ").append(category);
        }

        return result.toString();
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

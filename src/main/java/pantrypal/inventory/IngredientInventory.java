package pantrypal.inventory;

import pantrypal.general.control.Ui;

import java.util.HashMap;
import java.util.Map;

public class IngredientInventory {
    private Map<String, Ingredient> inventory;
    private Map<String, Double> lowStockAlerts;

    public IngredientInventory() {
        inventory = new HashMap<>();
        lowStockAlerts = new HashMap<>();
    }

    // Add new ingredient method
    public void addNewIngredient(String name, double quantity, Unit unit, Category category) {
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


    // Increase ingredient quantity
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

    // Decrease ingredient quantity
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


        // Set low stock alert
    public void setAlert(String name, double threshold) {
        lowStockAlerts.put(name, threshold);
    }

    // Get low stock alert
    public Map<String, Double> getLowStockAlerts() {
        return lowStockAlerts;
    }

    // Get inventory
    public Map<String, Ingredient> getInventory() {
        return inventory;
    }

    // Check stock
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

    //Find if ingredient is in stock
    public boolean isInInventory(String name, double quantity, Unit unit) {
        Ingredient ingredient = inventory.get(name);

        if (ingredient != null
                && ingredient.getQuantity() >= quantity
                && ingredient .getUnit() == unit) {
            return true;
        }
        return false;
    }

    // View low stock ingredients
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

    // Delete ingredient
    public void deleteIngredient(String name) {
        if (inventory.remove(name) != null) {
            lowStockAlerts.remove(name);
            System.out.println("Deleted " + name + " from inventory.");
        } else {
            System.out.println("Ingredient not found.");
        }
    }

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

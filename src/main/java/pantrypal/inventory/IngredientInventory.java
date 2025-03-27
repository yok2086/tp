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

    private void validateIngredient(String name, double quantity, Unit unit) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or empty.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
    }

    // Add new ingredient
    public void addNewIngredient(String name, double quantity, Unit unit) {
        validateIngredient(name, quantity, unit);
        if (!inventory.containsKey(name)) {
            inventory.put(name, new Ingredient(name, quantity, unit));
            System.out.println("Added " + name + ": " + quantity + " " + unit);
        } else {
            System.out.println(name + " already exists.");
        }
    }

    // Increase ingredient quantity
    public void increaseQuantity(String name, double quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Increase amount must be positive.");
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
        if (quantity <= 0) {
            throw new IllegalArgumentException("Decrease amount must be positive.");
        }
        Ingredient ingredient = inventory.get(name);
        if (ingredient != null) {
            if (ingredient.quantity >= quantity) {
                ingredient.quantity -= quantity;
                Ui.showMessage("Decreased " + name + " by " + quantity);
            } else {
                Ui.showMessage("Not enough " + name + " in stock.");
            }
        } else {
            Ui.showMessage("Ingredient not found");
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
                System.out.println(name + ": " + ingredient.quantity + " " + ingredient.unit);
            });
        }
    }

    // View low stock ingredients
    public void viewLowStock() {
        if (lowStockAlerts.isEmpty()) {
            Ui.showMessage("No low stock alerts set.");
            return;
        }

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
        if (!inventory.containsKey(name)) {
            throw new IllegalArgumentException("Cannot delete. Ingredient not found: " + name);
        }

        if (inventory.remove(name) != null) {
            lowStockAlerts.remove(name);
            System.out.println("Deleted " + name + " from inventory.");
        } else {
            System.out.println("Ingredient not found.");
        }
    }
}

package pantrypal.inventory;

import pantrypal.general.control.Ui;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

public class IngredientInventory {
    private Map<String, Ingredient> inventory;
    private Map<String, Double> lowStockAlerts;

    public IngredientInventory() {
        inventory = new HashMap<>();
        lowStockAlerts = new HashMap<>();
    }

    private void validateIngredient(String name, double quantity, Unit unit, LocalDate expiryDate) {
        assert name != null && !name.isEmpty() : "Ingredient name cannot be null or empty";
        assert quantity > 0 : "Quantity must be positive";
        assert unit != null : "Unit cannot be null or empty";
    }

    // Add new ingredient
    public void addNewIngredient(String name, double quantity, Unit unit, LocalDate expiryDate) {
        validateIngredient(name, quantity, unit, expiryDate);
        if (!inventory.containsKey(name)) {
            inventory.put(name, new Ingredient(name, quantity, unit, expiryDate));
            System.out.println("Added " + name + ": " + quantity + " " + unit);
        } else {
            System.out.println(name + " already exists.");
        }
    }

    // Increase ingredient quantity
    public void increaseQuantity(String name, double quantity) {
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

    //Notify expired ingredient

    public void alertExpiredIngredient() {
        LocalDate today = LocalDate.now();
        boolean expired = false;

        for (Ingredient ingredient : inventory.values()) {
            if (ingredient.expiryDate != null && ingredient.expiryDate.isBefore(today)) {
                System.out.println("⚠ Warning: " + ingredient.name + " expired on " + ingredient.expiryDate + "!");
                expired = true;
            }
        }

        if (!expired) {
            System.out.println("No expired ingredients!");
        }
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
}

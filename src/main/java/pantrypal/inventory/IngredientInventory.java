package pantrypal.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IngredientInventory {
    private Map<String, Ingredient> inventory;
    private Map<String, Double> lowStockAlerts;

    public IngredientInventory() {
        inventory = new HashMap<>();
        lowStockAlerts = new HashMap<>();
    }

    // Add new ingredient
    public void addNewIngredient(String name, double quantity, String unit) {
        if (!inventory.containsKey(name)) {
            inventory.put(name, new Ingredient(name, quantity, unit));
            System.out.println("Added " + name + ": " + quantity + " " + unit);
        } else {
            System.out.println(name + " already exists.");
        }
    }

    // Increase ingredient quantity
    public void increaseQuantity(String name, double quantity, String unit) {
        Ingredient ingredient = inventory.get(name);
        if (ingredient != null && ingredient.unit.equals(unit)) {
            ingredient.quantity += quantity;
            System.out.println("Increased " + name + " by " + quantity + " " + unit);
        } else {
            System.out.println("Ingredient not found or unit mismatch.(" + unit + ")");
        }
    }

    // Decrease ingredient quantity
    public void decreaseQuantity(String name, double quantity, String unit) {
        Ingredient ingredient = inventory.get(name);
        if (ingredient != null && ingredient.unit.equals(unit)) {
            if (ingredient.quantity >= quantity) {
                ingredient.quantity -= quantity;
                System.out.println("Decreased " + name + " by " + quantity + " " + unit);
            } else {
                System.out.println("Not enough " + name + " in stock.");
            }
        } else {
            System.out.println("Ingredient not found or unit mismatch.(" + unit + ")");
        }
    }

    // Set low stock alert
    public void setAlert(String name, double threshold, String unit) {
        if (inventory.containsKey(name)) {
            lowStockAlerts.put(name, threshold);
            System.out.println("Set low stock alert for " + name + " at " + threshold + " " + unit);
        } else {
            System.out.println("Ingredient not found.");
        }
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
        boolean found = false;
        for (Map.Entry<String, Double> alert : lowStockAlerts.entrySet()) {
            Ingredient ingredient = inventory.get(alert.getKey());
            if (ingredient != null && ingredient.quantity <= alert.getValue()) {
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

    public static void main(String[] args) {
        IngredientInventory inventory = new IngredientInventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter command:");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length == 0) {
                continue;
            }
            String command = parts[0];

            switch (command) {
            case "addNewIngredient":
                if (parts.length == 4) {
                    inventory.addNewIngredient(parts[1], Double.parseDouble(parts[2]), parts[3]);
                } else {
                    System.out.println("Usage: addNewIngredient <name> <quantity> <unit>");
                }
                break;

            case "increaseQuantity":
                if (parts.length == 4) {
                    inventory.increaseQuantity(parts[1], Double.parseDouble(parts[2]), parts[3]);
                } else {
                    System.out.println("Usage: increaseQuantity <name> <quantity> <unit>");
                }
                break;

            case "decreaseQuantity":
                if (parts.length == 4) {
                    inventory.decreaseQuantity(parts[1], Double.parseDouble(parts[2]), parts[3]);
                } else {
                    System.out.println("Usage: decreaseQuantity <name> <quantity> <unit>");
                }
                break;

            case "setAlert":
                if (parts.length == 4) {
                    inventory.setAlert(parts[1], Double.parseDouble(parts[2]), parts[3]);
                } else {
                    System.out.println("Usage: setAlert <name> <threshold> <unit>");
                }
                break;

            case "checkStock":
                inventory.checkStock();
                break;

            case "viewLowStock":
                inventory.viewLowStock();
                break;

            case "deleteIngredient":
                if (parts.length == 2) {
                    inventory.deleteIngredient(parts[1]);
                } else {
                    System.out.println("Usage: deleteIngredient <name>");
                }
                break;

            case "exit":
                System.out.println("Exiting Inventory System...");
                scanner.close();
                return;

            default:
                System.out.println("Invalid command.");
                break;
            }
        }
    }
}

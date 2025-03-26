package pantrypal.general.commands.inventory;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddIngredient extends InventoryCommand {
    private String name;
    private double quantity;
    private String unit;

    public AddIngredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public AddIngredient() {
        super("addNewIngredient <name> <quantity> <unit>", "Adding new ingredient");
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        if (!inventory.getInventory().containsKey(name)) {
            inventory.addNewIngredient(name, quantity, unit);
            Ui.printAddIngredientMessage(name, quantity, unit);
        } else {
            Ui.printIngredientExists(name);
        }
    }
}

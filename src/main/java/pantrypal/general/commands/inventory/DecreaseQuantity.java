package pantrypal.general.commands.inventory;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class DecreaseQuantity extends InventoryCommand {
    private String name;
    private double quantity;
    private String unit;

    public DecreaseQuantity(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public DecreaseQuantity() {
        super("decreaseQuantity <name> <quantity> <unit>", "Decrease quantity of ingredient");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        inventory.decreaseQuantity(name, quantity, unit);

    }
}

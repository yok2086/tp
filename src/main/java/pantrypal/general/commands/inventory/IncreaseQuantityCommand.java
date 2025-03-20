package pantrypal.general.commands.inventory;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class IncreaseQuantityCommand extends Command {
    private String name;
    private double quantity;
    private String unit;

    public IncreaseQuantityCommand() {
        super("increaseQuantity <name> <quantity> <unit>","Increase quantity of ingredient");
    }

    public IncreaseQuantityCommand(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        inventory.increaseQuantity(name, quantity, unit);
    }

}

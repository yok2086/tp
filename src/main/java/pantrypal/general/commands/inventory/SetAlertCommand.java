package pantrypal.general.commands.inventory;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class SetAlertCommand extends Command {
    private String name;
    private double threshold;
    private String unit;

    public SetAlertCommand() {
        super("setAlert <name> <threshold> <unit>", "Sets the alert for a specific ingredient");
    }

    public SetAlertCommand(String name, double threshold, String unit) {
        this.name = name;
        this.threshold = threshold;
        this.unit = unit;
    }


    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList, PlanPresets planPresets, RecipeManager recipeManager, Scanner in) {
        inventory.setAlert(name, threshold, unit);
    }
}

package pantrypal.general.commands.inventory;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class SetAlert extends InventoryCommand {
    private String name;
    private double threshold;
    private String unit;

    public SetAlert() {
        super("setAlert <name> <threshold>",
                "Sets the alert for a specific ingredient");
    }

    public SetAlert(String name, double threshold) {
        this.name = name;
        this.threshold = threshold;
    }


    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, WeeklySchedule week, Scanner in) {
        if (inventory.getInventory().containsKey(name)) {
            inventory.setAlert(name, threshold);
            Ui.printSetAlertMessage(name, threshold);
        } else {
            Ui.printIngredientNotFoundMessage();
        }
    }
}

package pantrypal.general.commands.inventory;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public abstract class InventoryCommand extends Command {

    public InventoryCommand(String commandInstruction, String commandDescription) {
        super(commandInstruction, commandDescription);
    }

    public InventoryCommand() {}

    @Override
    public abstract void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                                 RecipeManager recipes, WeeklySchedule week, Scanner in);


}

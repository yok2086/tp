package pantrypal.general.commands.general;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class GeneralCommand extends Command {

    public GeneralCommand(String commandInstruction, String commandDescription) {
        super(commandInstruction, commandDescription);
    }

    public GeneralCommand() {}

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets, RecipeManager recipes, Scanner in) {

    }
}

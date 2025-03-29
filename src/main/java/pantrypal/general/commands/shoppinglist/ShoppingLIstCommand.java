package pantrypal.general.commands.shoppinglist;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class ShoppingLIstCommand extends Command {


    public ShoppingLIstCommand(String commandInstruction, String commandDescription) {
        super(commandInstruction, commandDescription);
    }

    public ShoppingLIstCommand() {}

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {

    }
}

package pantrypal.general.commands.recipe;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public abstract class RecipeCommand extends Command {

    public RecipeCommand(String commandInstruction, String commandDescription) {
        super(commandInstruction, commandDescription);
    }

    public RecipeCommand() {}

    @Override
    public abstract void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                                 MealPlanManager plans, Scanner in);
}

package pantrypal.general.commands.shoppinglist;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public abstract class ShoppingListCommand extends Command {


    public ShoppingListCommand(String commandInstruction, String commandDescription) {
        super(commandInstruction, commandDescription);
    }

    public ShoppingListCommand() {}

    @Override
    public abstract void execute(Ui ui, IngredientInventory inventory, ShoppingList list,
                                 RecipeManager recipes, MealPlanManager plans, Scanner in);
}

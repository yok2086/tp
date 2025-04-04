package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class NullCommand extends Command {
    private String errorMessage;

    public NullCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,
                        RecipeManager recipes, MealPlanManager plans, Scanner in) {
        ui.printInvalidCommandMessage(errorMessage);
    }

}

package pantrypal.general.commands.shoppinglist;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class ViewShoppingList extends ShoppingListCommand {

    public ViewShoppingList() {
        super("viewShoppingList", "View Shopping List");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,
                        RecipeManager recipes, MealPlanManager plans, Scanner in) {
        Ui.printLine();
        list.displayList(); // Error handling is inside the function
    }
}

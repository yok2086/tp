package pantrypal.general.commands.shoppinglist;

import java.util.Scanner;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListGenerator;

public class GenerateShoppingList extends ShoppingListCommand {

    public GenerateShoppingList() {
        super("generateShoppingList", "Auto-generate shopping list");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,
                        RecipeManager recipes, MealPlanManager plans, Scanner in) {
        ShoppingListGenerator shoppingListGenerator = new ShoppingListGenerator(inventory);
        ShoppingList newShoppingList = shoppingListGenerator.generateShoppingList();
        list.copyFrom(newShoppingList);
        Ui.showMessage("Shopping list has been cleared! \nShopping list has been auto-generated as below:");
        new ViewShoppingList();
    }
}

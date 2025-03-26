package pantrypal.general.commands.shoppinglist;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListGenerator;

import java.util.Scanner;

public class GenerateShoppingList extends ShoppingLIstCommand {

    public GenerateShoppingList() {
        super("generateShoppingList", "Auto-generate shopping list");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        ShoppingListGenerator shoppingListGenerator = new ShoppingListGenerator(inventory);
        ShoppingList newShoppingList = shoppingListGenerator.generateShoppingList();
        list.copyFrom(newShoppingList);
        Ui.showMessage("Shopping list has been cleared! \nShopping list has been auto-generated.");
    }
}

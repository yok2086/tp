package pantrypal.general.commands.shoppinglist;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class MarkShoppingItemAsPurchased extends ShoppingListCommand {
    private String ingredientName;

    public MarkShoppingItemAsPurchased(String ingredientName) {
        super("markShoppingItemAsPurchased <ingredient_name>", "Mark a shopping list item as purchased");
        this.ingredientName = ingredientName;
    }

    public MarkShoppingItemAsPurchased() {
        super("markShoppingItemAsPurchased <ingredient_name>", "Mark a shopping list item as purchased");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,
                        RecipeManager recipes, MealPlanManager plans, Scanner in) {
        boolean marked = list.markItemAsPurchased(ingredientName);
        if (marked) {
            ui.showMessage("Marked '" + ingredientName + "' as purchased.");
        } else {
            ui.showMessage("Item '" + ingredientName + "' not found in the shopping list.");
        }
    }
}

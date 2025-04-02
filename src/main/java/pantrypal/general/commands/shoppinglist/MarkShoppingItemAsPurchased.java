package pantrypal.general.commands.shoppinglist;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class MarkShoppingItemAsPurchased extends ShoppingListCommand {
    private String ingredientName;

    public MarkShoppingItemAsPurchased(String ingredientName) {
        super("markItemAsPurchased <ingredient_name>", "Mark a shopping list item as purchased");
        this.ingredientName = ingredientName;
    }

    public MarkShoppingItemAsPurchased() {
        super("markItemAsPurchased <ingredient_name>", "Mark a shopping list item as purchased");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        boolean marked = list.markItemAsPurchased(ingredientName);
        if (marked) {
            ui.showMessage("Marked '" + ingredientName + "' as purchased.");
        } else {
            ui.showMessage("Item '" + ingredientName + "' not found in the shopping list.");
        }
    }
}

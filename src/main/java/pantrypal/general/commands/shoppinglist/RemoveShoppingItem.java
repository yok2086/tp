package pantrypal.general.commands.shoppinglist;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemoveShoppingItem extends ShoppingLIstCommand {
    private String name;

    public RemoveShoppingItem() {
        super("removeShoppingItem <name>", "Delete an item from the ShoppingList");
    }

    public RemoveShoppingItem(String name) {
        super("removeShoppingItem <name>", "Delete an item from the ShoppingList");
        this.name = name;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        if (list == null) {
            ui.showMessage("Error: ShoppingList is not initialized.");
            return;
        }
        boolean removed = list.removeItem(name);
        if (removed) {
            ui.showMessage("Removed '" + name + "' from the shopping list.");
        } else {
            ui.showMessage("Item '" + name + "' not found in the shopping list.");
        }
    }
}

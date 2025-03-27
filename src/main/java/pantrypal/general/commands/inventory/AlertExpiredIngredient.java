package pantrypal.general.commands.inventory;

import pantrypal.general.control.Ui;
import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.time.LocalDate;
import java.util.Scanner;


public class AlertExpiredIngredient extends InventoryCommand {

public AlertExpiredIngredient() {
    super("alertExpiredIngredient",
            "Alerts the user about expired ingredients in the inventory.");
}

@Override
public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                    RecipeManager recipes, Scanner in) {
    inventory.alertExpiredIngredient();

}
}


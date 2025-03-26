package pantrypal.general.commands.inventory;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class DeleteIngredient extends InventoryCommand {
    private String name;

    public DeleteIngredient() {
        super("deleteIngredient <name>", "Delete an ingredient from the inventory");
    }

    public DeleteIngredient(String name) {
        this.name = name;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        inventory.deleteIngredient(name);
    }
}

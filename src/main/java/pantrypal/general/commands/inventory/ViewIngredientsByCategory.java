package pantrypal.general.commands.inventory;

import pantrypal.general.control.Ui;
import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.inventory.Category;
import java.util.Scanner;

public class ViewIngredientsByCategory extends InventoryCommand {
    private Category category;

    public ViewIngredientsByCategory() {
        super("viewIngredientsByCategory <category>", "View ingredients by " +
                "their category");
    }

    public ViewIngredientsByCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        boolean found = false;

        for (Ingredient ingredient : inventory.getInventory().values()) {
            if (ingredient.getCategory() == category) {
                ui.showMessage(ingredient.toString());
                found = true;
            }
        }

        // If no ingredients are found in the category, notify the user
        if (!found) {
            ui.showMessage("No ingredients found in category: " + category);
        }
    }
}

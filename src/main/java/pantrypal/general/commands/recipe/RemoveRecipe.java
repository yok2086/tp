package pantrypal.general.commands.recipe;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemoveRecipe extends RecipeCommand {
    private String recipeName;

    public RemoveRecipe() {
        super("removeRecipe <name>", "Removes a recipe");
    }

    public RemoveRecipe(String recipeName) {
        this.recipeName = recipeName;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, WeeklySchedule week, Scanner in) {
        recipes.removeRecipe(recipeName);
    }
}

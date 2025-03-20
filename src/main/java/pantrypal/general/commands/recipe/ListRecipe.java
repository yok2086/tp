package pantrypal.general.commands.recipe;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class ListRecipe extends Command {

    public ListRecipe() {
        super("listRecipes", "List all recipes");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList, PlanPresets planPresets, RecipeManager recipeManager, Scanner in) {
        recipeManager.listRecipe();
    }
}

package pantrypal.general.commands.recipe;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class ViewRecipe extends Command {
    private String recipeName;

    public ViewRecipe() {
        super("viewRecipe","View a specific recipe");
    }

    public ViewRecipe(String recipeName) {
        this.recipeName = recipeName;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList, PlanPresets planPresets, RecipeManager recipeManager, Scanner in) {
        recipeManager.showRecipe(recipeName);
    }
}

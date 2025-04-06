package pantrypal.general.commands.recipe;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class ViewRecipe extends RecipeCommand {
    private String recipeName;

    public ViewRecipe() {
        super("viewRecipe <Recipe Name>","View a specific recipe");
    }

    public ViewRecipe(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {

        recipes.showRecipe(recipeName);
    }

}

package pantrypal.general.commands.mealplan;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemoveRecipeFromPlan extends Command {
    private int recipeIndex;
    private int planIndex;

    public RemoveRecipeFromPlan() {
        super("removeFromPlan <recipe index>" , "Remove a recipe to a plan");
    }

    public RemoveRecipeFromPlan(int recipeIndex, int planIndex) {
        this.recipeIndex = recipeIndex;
        this.planIndex = planIndex;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        presets.removeRecipeFromPlan(recipes, recipeIndex, planIndex);
        System.out.println("Recipe " + recipeIndex + 1 + " removed from plan  " + planIndex + 1);
    }
}

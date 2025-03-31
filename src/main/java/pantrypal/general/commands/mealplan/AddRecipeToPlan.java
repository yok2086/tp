package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddRecipeToPlan extends MealPlanCommand {
    private int recipeIndex;
    private int planIndex;
    private int mealIndex;

    public AddRecipeToPlan() {
        super("addToPlan <recipe index> <plan index> <meal index>",
                "Add a recipe to a plan");
    }

    public AddRecipeToPlan(int recipeIndex, int planIndex, int mealIndex) {
        this.recipeIndex = recipeIndex;
        this.planIndex = planIndex;
        this.mealIndex = mealIndex;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        String mealName = presets.addRecipeToPlan(recipes, recipeIndex, planIndex, mealIndex);
        if (mealName.equals("NULL")) {
            System.out.println("Invalid meal index entered");
        } else {
            System.out.println("Recipe " + recipes.getRecipeList().get(recipeIndex-1).getName() +
                    " added to plan " + (planIndex-1) + " for " + mealName);
        }

    }
}

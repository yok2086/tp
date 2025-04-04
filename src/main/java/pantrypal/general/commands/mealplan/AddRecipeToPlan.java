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
    private String mealName;

    public AddRecipeToPlan() {
        super("addRecipeToPlan <recipe index> <plan index> <meal name>",
                "Add a recipe to a particular meal of a plan");
    }

    public AddRecipeToPlan(int recipeIndex, int planIndex, String mealName) {
        this.recipeIndex = recipeIndex;
        this.planIndex = planIndex;
        this.mealName = getMealName(mealName);
    }


    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {

        presets.addRecipeToPlan(recipes, recipeIndex, planIndex, this.mealName);

        System.out.println("Recipe " + recipes.getRecipeList().get(recipeIndex).getName() +
                " added to plan " + planIndex + " for " + this.mealName);

    }
}

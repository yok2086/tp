package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.mealplan.Plan;
import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddRecipeToPlan extends MealPlanCommand {

    int planIndex;
    int recipeIndex;
    Plan.MealType mealType;

    public AddRecipeToPlan() {
        super("addPlanToList <plan name>", "Add a new plan to the list");
    }

    public AddRecipeToPlan(int planIndex, int recipeIndex, String mealType) {
        try {
            this.planIndex = planIndex;
            this.recipeIndex = recipeIndex;
            this.mealType = Plan.MealType.valueOf(mealType.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid meal type: " + mealType);
        }
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in){
        try {
            if (recipeIndex >= recipes.getRecipeList().size()) {
                throw new ArrayIndexOutOfBoundsException("Recipe index out of bounds");
            }
            Recipe recipe = recipes.getRecipeList().get(recipeIndex);
            plans.getPlanDetails(planIndex).addRecipeToPlan(recipe, mealType);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Invalid plan index");
        }
    }
}

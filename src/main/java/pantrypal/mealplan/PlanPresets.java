package pantrypal.mealplan;

import java.util.ArrayList;
import pantrypal.general.commands.mealplan.AddRecipeToPlan;
import pantrypal.recipe.RecipeManager;

/**
 * Overarching handler for created meal plans and month-based assignment of meal plans
 * selected meal plan is for individual, non-calendar based view
 * calendar added meal plan is for added structure pertaining to the associated month
 */


public class PlanPresets {
    private static final int PRESET_SIZE = 11; //padding element + 10 elements
    private static final int NUMBER_OF_MONTHS = 12;
    ArrayList<MealPlan> plans;

    public PlanPresets() {
        loadPlans();
    }

    public void loadPlans() {
        // load plans from file, if no file found, start afresh
        plans = new ArrayList<MealPlan>();
    }

    public void addNewPlan(){
        plans.add(new MealPlan());
        new AddRecipeToPlan(); //call the command to add a new recipe
    }

    public void addRecipeToPlan(RecipeManager recipes, int recipeIndex, int planIndex){
        plans.get(planIndex).addRecipe(recipes, recipeIndex);
    }

    public void removePlan(int planIndex) {
        plans.remove(planIndex);
    }

    public void removeRecipeFromPlan(int planIndex, int recipeIndex) {
        plans.get(recipeIndex).removeRecipe(planIndex);
    }

    public void viewPlans(){
        for (MealPlan plan : plans) {
            System.out.println(plan);
        }
    }


}

package pantrypal.mealplan;

import pantrypal.recipe.RecipeManager;

import java.util.ArrayList;

/**
 * Overarching handler for created meal plans and month-based assignment of meal plans
 * selected meal plan is for individual, non-calendar based view
 * calendar added meal plan is for added structure pertaining to the associated month
 */


public class PlanPresets {
    private static final int PRESET_SIZE = 11; //padding element + 10 elements
    private static final int NUMBER_OF_MONTHS = 12;
    protected ArrayList<MealPlan> plans;

    public PlanPresets() {
        plans = new ArrayList<>();
    }

    public void addNewPlan(int duration) {
        plans.add(new MealPlan(duration));
    }

    public void addRecipeToPlan(RecipeManager recipeList, int recipeIndex, int planIndex) {
        plans.get(planIndex).addRecipeToPlan(recipeList, recipeIndex);
    }

    public void removeRecipeFromPlan(RecipeManager recipeList, int recipeIndex, int planIndex) {
        plans.get(planIndex).removeRecipeFromPlan(recipeList, recipeIndex);
    }

    public void removePlan(int planIndex) {
        plans.remove(planIndex);
    }

    public void viewPlans() {
        for (MealPlan plan : plans) {
            System.out.println(plan);
        }
    }
}

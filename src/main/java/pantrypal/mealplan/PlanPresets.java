package pantrypal.mealplan;

import java.util.ArrayList;
import pantrypal.recipe.RecipeManager;

/**
 * Overarching handler for created meal plans and month-based assignment of meal plans
 * selected meal plan is for individual, non-calendar based view
 * calendar added meal plan is for added structure pertaining to the associated month
 */


public class PlanPresets {
    private ArrayList<MealPlan> plans;

    public PlanPresets() {
        loadPlans();
    }

    private void loadPlans() {
        // load plans from file, if no file found, start afresh
        plans = new ArrayList<>();
    }

    private String getMealName(int mealIndex) {
        switch(mealIndex){
        case 1 -> {
            return "Breakfast";
        }
        case 2 -> {
            return "Lunch";
        }
        case 3 -> {
            return "Dinner";
        }
        default -> {
            return  "NULL";
        }
        }
    }

    public void addNewPlan(String planName){
        plans.add(new MealPlan(planName.isEmpty() ? "default" : planName));
    }

    public String addRecipeToPlan(RecipeManager recipes, int recipeIndex, int planIndex, int mealIndex) {
        plans.get(planIndex).addRecipe(recipes, recipeIndex, mealIndex);
        return getMealName(mealIndex);
    }

    public void removePlan(int planIndex) {
        plans.remove(planIndex);
    }

    public String removeRecipeFromPlan(int planIndex, int mealIndex) {
        plans.get(planIndex).removeRecipe(mealIndex);
        return getMealName(mealIndex);
    }

    public void viewPresets(){
        int count = 1;
        for (MealPlan plan : plans) {
            System.out.println(count++ + ": " + plan.getPlanName());
        }
    }

    public void viewPlan(int planIndex) {
        System.out.println(plans.get(planIndex).toString());
    }

    public void findPresets(String planName){
        for (MealPlan plan : plans) {
            if (plan.getPlanName().contains(planName)){
                System.out.println(plan.getPlanName());
            }
        }
    }

    public ArrayList<MealPlan> getPlans() {
        return plans;
    }
}

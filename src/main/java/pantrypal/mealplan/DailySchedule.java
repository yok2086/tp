package pantrypal.mealplan;

import pantrypal.inventory.IngredientInventory;
import pantrypal.recipe.Recipe;

/**
 *  shows a selected meal plan for a day
 */

public class DailySchedule {

    private MealPlan selectedMealPlan = null;
    private boolean used = false;
    private boolean executed = false;

    public DailySchedule(PlanPresets preset, int planIndex) {
        setPlan(preset, planIndex);
    }

    public void setPlan(PlanPresets planPresets, int planIndex){
        MealPlan planToCopy = planPresets.getPlans(planIndex);
        this.selectedMealPlan = new MealPlan(planToCopy.getPlanName());
        this.selectedMealPlan.setRecipes(planToCopy.getRecipes());
        this.used = true;
    }

    public void removePlan(){
        this.selectedMealPlan = null;
        this.used = false;
    }

    public void editPlan(){

    }

    public void showDay(){
        if (!used){
            System.out.println("No plan selected");
        } else {
            System.out.println(selectedMealPlan.toString());
        }
    }

    /**
     * attempt to carry out removal. If this fails, add to a running list of
     * Ingredients and the values the Recipe requires
     *
     * @param inventory attached user inventory
     */

    public void executePlan(IngredientInventory inventory){
        for (Recipe recipe : selectedMealPlan.getRecipes()){
            System.out.println(recipe.toString());
        }
    }

    public boolean planIsUsed(){
        return used;
    }
}

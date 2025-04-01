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

    public DailySchedule() {
    }

    public void setPlan(PlanPresets planPresets, int planIndex){
        selectedMealPlan = planPresets.getPlans().get(planIndex);
        this.used = true;
    }

    public void showDay(){
        if (!used){
            System.out.println("No plan selected");
        } else {
            System.out.println(selectedMealPlan.toString());
        }
    }

    public void executePlan(IngredientInventory inventory){
        for (Recipe recipe : selectedMealPlan.getRecipes()){
            
        }
    }

    public boolean planIsUsed(){
        return used;
    }
}

package pantrypal.mealplan;

import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;
import pantrypal.recipe.Recipe;

import java.util.ArrayList;
import java.util.Scanner;

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
        MealPlan planToCopy = planPresets.getPlanDetails(planIndex);
        this.selectedMealPlan = new MealPlan(planToCopy.getPlanName());
        this.selectedMealPlan.setRecipes(planToCopy.getRecipes());
        this.used = true;
    }

    public void removePlan(){
        this.selectedMealPlan = null;
        this.used = false;
    }


    /**
     * attempt to carry out removal. If this fails, add to a running list of
     * Ingredients and the values the Recipe requires
     *
     * @param inventory attached user inventory
     */

    public void executePlan(IngredientInventory inventory){
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (Recipe recipe : selectedMealPlan.getRecipes()){
            if (recipe == null) {
                continue;
            }
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (!inventory.findInventory(ingredient.getName(), ingredient.getQuantity(), ingredient.getUnit())) {
                    ingredients.add(ingredient);
                }
            }
        }

        if (ingredients.isEmpty()) {
            for (Recipe recipe : selectedMealPlan.getRecipes()){
                if (recipe == null) {
                    continue;
                }
                for (Ingredient ingredient : recipe.getIngredients()) {
                    inventory.decreaseQuantity(ingredient.getName(), ingredient.getQuantity());
                }
            }
            this.executed = true;
            return;
        }

        System.out.println("There are insufficient quantities of these ingredients, " +
                            "please also check their expiry dates.");
        for (Ingredient ingredient : ingredients){
            System.out.println(ingredient.getName() + ": " + ingredient.getQuantity() + " " + ingredient.getUnit());
        }
    }

    public boolean planIsExecuted(){
        return executed;
    }

    public boolean planIsUsed(){
        return used;
    }

    public void editPlan(Scanner in){ }

    public String getSelectedPlanName(){
        return selectedMealPlan.getPlanName();
    }

    public void showDay(){
        if (!used){
            System.out.println("No plan selected");
        } else {
            System.out.println(selectedMealPlan.toString());
        }
    }



}

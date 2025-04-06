package pantrypal.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.recipe.Recipe;


public class Plan {
    private Recipe[] planRecipes = new Recipe[3]; //Maximum 3 recipes per plan
    private String planName;


    public Plan(String planName) {
        this.planName = planName.isEmpty() ? "default" : planName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Recipe[] getPlanRecipes() {
        return planRecipes;
    }

    public void addRecipeToPlan(Recipe recipe, MealType mealType) {
        int mealIndex = mealType.ordinal();
        if (planRecipes[mealIndex] == null) {
            planRecipes[mealIndex] = recipe;
        } else {
            Ui.showMessage("A recipe is already assigned to this meal.");
        }
    }

    public void removeRecipeFromPlan(MealType mealType) {
        int mealIndex = mealType.ordinal();
        if (planRecipes[mealIndex] != null) {
            planRecipes[mealIndex] = null;
        } else {
            Ui.showMessage("This meal has no recipe.");
        }
    }

    @Override
    public String toString() {
        int mealIndexCount = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Plan Name: ").append(planName).append("\n");
        sb.append("Recipes:\n");
        for (Recipe recipe : planRecipes) {
            if (recipe != null) {
                sb.append(MealType.values()[mealIndexCount]).append(" ").append(recipe.getName()).append("\n");
            }
            mealIndexCount++;
        }
        return sb.toString();
    }
}

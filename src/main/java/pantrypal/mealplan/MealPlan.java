package pantrypal.mealplan;

import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;

public class MealPlan {
    private static final int PLAN_SIZE = 3;
    private final Recipe[] recipes = new Recipe[3]; //recipes that are part of this meal plan
    private final String planName;

    public MealPlan(String planName){
        for (int i = 0; i < PLAN_SIZE; i++) {
            recipes[i] = null;
        }
        this.planName = planName;
    }

    public void addRecipe(RecipeManager recipeList, int recipeIndex, int mealIndex) {

        try {
            switch (mealIndex) {
            case 1:
            case 2:
            case 3:
                if (recipeIndex <= 0 || recipeIndex >= recipeList.getRecipeList().size()) {
                    throw new InvalidRecipeIndexException("Invalid recipe index");
                }
                recipes[mealIndex] = recipeList.getRecipeList().get(recipeIndex);
                break;

            default:
                throw new InvalidMealIndexException("Invalid meal index");
            }
        } catch (InvalidMealIndexException e) {
            System.out.println(e.getMessage());
        } catch (InvalidRecipeIndexException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void removeRecipe(int mealIndex) {
        recipes[mealIndex] = null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(planName);
        res.append('\n');
        for (Recipe i : recipes) {
            res.append(i.getContent());
        }

        return res.toString();
    }

    public String getPlanName() {
        return planName;
    }

}

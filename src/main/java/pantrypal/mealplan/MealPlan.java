package pantrypal.mealplan;

import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;


import java.util.ArrayList;


public class MealPlan {
    private final int duration;
    private ArrayList <Recipe> recipes = new ArrayList<>(); //recipes that are part of this meal plan

    public MealPlan(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    //Leaving sanitation to CLI section

    public void addRecipeToPlan(RecipeManager recipeList, int recipeIndex) {
        recipes.add(recipeList.getRecipeList().get(recipeIndex));
    }

    public void removeRecipeFromPlan(RecipeManager recipeList, int recipeIndex) {
        recipes.remove(recipeList.getRecipeList().get(recipeIndex));
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Duration: ").append(getDuration()).append("\n");
        for (Recipe i : recipes) {
            res.append(i.getContent());
        }

        return res.toString();
    }
}

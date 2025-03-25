package pantrypal.mealplan;

import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;


import java.util.ArrayList;


public class MealPlan {
    private ArrayList <Recipe> recipes = new ArrayList<>(); //recipes that are part of this meal plan

    public MealPlan(){
    }

    public void addRecipe(RecipeManager recipeList, int recipeIndex) {
        recipes.add(recipeList.getRecipeList().get(recipeIndex));
    }

    public void removeRecipe(int recipeIndex) {
        recipes.remove(recipeIndex);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Recipe i : recipes) {
            res.append(i.getContent());
        }

        return res.toString();
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}

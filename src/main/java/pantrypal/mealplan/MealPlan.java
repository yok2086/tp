package pantrypal.mealplan;

import pantrypal.inventory.Ingredient;
import pantrypal.recipe.Instruction;
import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;

import java.util.Scanner;

public class MealPlan {
    private static final int PLAN_SIZE = 3;
    private Recipe[] recipes = new Recipe[3]; //recipes that are part of this meal plan
    private String planName;

    public MealPlan(String planName){
        for (int i = 0; i < PLAN_SIZE; i++) {
            recipes[i] = null;
        }
        this.planName = planName;
    }

    private int getMealIndex(String mealName) {
        switch (mealName) {
        case "BREAKFAST" -> {
            return 0;
        }
        case "LUNCH" -> {
            return 1;
        }
        case "DINNER" -> {
            return 2;
        }
        default -> throw new InvalidMealIndexException("Invalid meal index");
        }
    }

    public void addRecipe(RecipeManager recipeList, int recipeIndex, String mealName) {

        int mealIndex;

        try {
            mealIndex = getMealIndex(mealName);

            if (recipeIndex <= 0 || recipeIndex >= recipeList.getRecipeList().size()) {
                throw new InvalidRecipeIndexException("Invalid recipe index");
            }

            Recipe recipeToCopy = recipeList.getRecipeList().get(recipeIndex);
            recipes[mealIndex] = new Recipe(recipeToCopy.getName());

            for (Instruction instruction : recipeToCopy.getInstructions()) {
                recipes[mealIndex].addInstruction(instruction);
            }

            for (Ingredient ingredient : recipeToCopy.getIngredients()) {
                recipes[mealIndex].addIngredient(ingredient);
            }


        } catch (InvalidMealIndexException e) {
            System.out.println(e.getMessage());

        } catch (InvalidRecipeIndexException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void editRecipe(Scanner in){

    }

    public void removeRecipe(String mealName) {
        recipes[getMealIndex(mealName)] = null;
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

    public void editPlanName(String newPlanName) {
        this.planName = newPlanName;
    }

    public void setRecipes(Recipe[] recipes) {
        this.recipes = recipes;
    }

    public Recipe[] getRecipes(){
        return recipes;
    }

}

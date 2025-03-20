package pantrypal.general.commands.recipe;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddRecipe extends Command {
    private String recipeName;
    private String stepContent;
    private String content;


    public AddRecipe() {
        super("addRecipe", "Add a recipe");
    }


    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList, PlanPresets planPresets, RecipeManager recipeManager, Scanner in) {
        Boolean isFinished = false;
        System.out.println("Please Input Recipe Name: ");
        recipeName = in.nextLine();
        Recipe recipe = recipeManager.addRecipe(recipeName);

        int stepNumber = 1;
        while (!isFinished) {
            System.out.println("Please Input Step: <When done, type exit>");
            stepContent = in.nextLine();
            if (stepContent.equals("exit")) {
                isFinished = true;
            } else {
                recipeManager.addRecipeInstruction(recipe, stepNumber++, stepContent);
            }
        }
        System.out.println("Recipe Added Successfully!");
    }
}

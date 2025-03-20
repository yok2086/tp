package pantrypal.general.commands.mealplan;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddRecipeToPlan extends Command {
    private int recipeIndex;
    private int planIndex;

    public AddRecipeToPlan() {
        super("addToPlan <recipe index>" , "Add a recipe to a plan");
    }

    public AddRecipeToPlan(int recipeIndex, int planIndex) {
        this.recipeIndex = recipeIndex;
        this.planIndex = planIndex;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList, PlanPresets planPresets, RecipeManager recipeManager, Scanner in) {
        planPresets.addRecipeToPlan(recipeManager, recipeIndex, planIndex);
        System.out.println("Recipe " + recipeIndex + 1 + " added to plan " + planIndex + 1);
    }
}

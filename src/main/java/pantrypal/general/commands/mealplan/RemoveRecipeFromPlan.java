package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemoveRecipeFromPlan extends MealPlanCommand {
    private int planIndex;
    private int mealIndex;

    public RemoveRecipeFromPlan() {
        super("removeFromPlan <recipe index>",
                "Remove a recipe to a plan");
    }

    public RemoveRecipeFromPlan(int planIndex, int mealIndex) {
        this.planIndex = planIndex;
        this.mealIndex = mealIndex;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        String mealName = presets.removeRecipeFromPlan(mealIndex, planIndex);
        if (mealName.equals("NULL")) {
            System.out.println("The corresponding meal did not have a recipe allocated.");
        } else {
            System.out.println("Recipe for " + mealName + " removed from plan  " + (planIndex - 1));
        }

    }
}

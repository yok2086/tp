package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemoveRecipeFromPlan extends MealPlanCommand {
    private int planIndex;
    private String mealName;

    public RemoveRecipeFromPlan() {
        super("removeFromPlan <recipe index> <meal name>" ,
                "Remove a recipe from a particular meal of a plan");
    }

    public RemoveRecipeFromPlan(int planIndex, String mealName) {
        this.planIndex = planIndex;
        this.mealName = getMealName(mealName);
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, WeeklySchedule week, Scanner in) {
        presets.removeRecipeFromPlan(planIndex, mealName);
        System.out.println("Recipe for " + mealName + " removed from plan " + planIndex);

    }
}

package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemovePlan extends MealPlanCommand {
    private int planIndex;

    public RemovePlan() {
        super("removePlan <plan name>", "Remove a specific meal plan");
    }

    public RemovePlan(int planIndex) {
        this.planIndex = planIndex;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes, MealPlanManager plans, Scanner in) {
        if (planIndex < 0 || planIndex >= plans.getPlanList().length) {
            Ui.showMessage("Invalid plan index. Please try again.");
            return;
        }
        plans.removePlanFromList(planIndex);
        Ui.showMessage("Plan removed successfully.");
    }
}

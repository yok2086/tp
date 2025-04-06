package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class ViewPlan extends MealPlanCommand {
    private int planIndex;

    public ViewPlan() {
        super("viewPlan <plan name>", "View a specific meal plan");
    }

    public ViewPlan(int planIndex) {
        this.planIndex = planIndex;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes, MealPlanManager plans, Scanner in) {
        plans.viewPlan(planIndex);
    }
}

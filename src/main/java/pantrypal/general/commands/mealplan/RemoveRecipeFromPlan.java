package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.mealplan.Plan;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemoveRecipeFromPlan extends MealPlanCommand {

    int planIndex;
    Plan.MealType mealType;

    public RemoveRecipeFromPlan() {
        super("addPlanToList <plan name>", "Add a new plan to the list");
    }

    public RemoveRecipeFromPlan(int planIndex, String mealType) {
        try {
            this.planIndex = planIndex;
            this.mealType = Plan.MealType.valueOf(mealType.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid meal type: " + mealType);
        }
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in){
        try {
            plans.getPlanDetails(planIndex).removeRecipeFromPlan(mealType);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Invalid plan index");
        }
    }
}

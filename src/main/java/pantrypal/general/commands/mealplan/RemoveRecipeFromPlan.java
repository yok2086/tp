package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemoveRecipeFromPlan extends MealPlanCommand {

    int planIndex;
    String mealType;

    public RemoveRecipeFromPlan() {
        super("addPlanToList <plan name>", "Add a new plan to the list");
    }

    public RemoveRecipeFromPlan(int planIndex, String mealType) {
        this.planIndex = planIndex;
        this.mealType = mealType;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {
        try {
            plans.getPlanDetails(planIndex).removeRecipeFromPlan(getMealType(mealType));
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Invalid plan index given.");
        } catch (NullPointerException e) {
            System.out.println("Invalid meal type given.\nPlease enter only BREAKFAST, LUNCH OR DINNER");
        } catch (IllegalArgumentException e) {
            ui.showMessage("Invalid input given. Please refrain from unconventional datatypes");
        }
    }
}

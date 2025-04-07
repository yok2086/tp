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
        super("removeRecipeFromPlan <plan index> <breakfast/lunch/dinner> ",
                "Remove recipe from a meal plan");
    }

    public RemoveRecipeFromPlan(int planIndex, String mealType) {
        this.planIndex = planIndex;
        this.mealType = mealType;
    }

    public int getPlanIndex() {
        return planIndex;
    }

    public String getMealType() {
        return mealType;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {
        try {
            plans.getPlanDetails(planIndex).removeRecipeFromPlan(getMealType(mealType));
        } catch (IndexOutOfBoundsException e) {
            Ui.showMessage("Invalid plan index given.");
        } catch (NullPointerException e) {
            System.out.println("Invalid meal type given.\nPlease enter only BREAKFAST, LUNCH OR DINNER");
        } catch (IllegalArgumentException e) {
            Ui.showMessage("Invalid input given. Please refrain from unconventional datatypes");
        }
    }
}

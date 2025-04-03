
package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class ViewPresets extends MealPlanCommand {

    public ViewPresets() {
        super("viewPresets","View Meal Presets");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, WeeklySchedule week, Scanner in) {
        if (presets.getPlans().isEmpty()){
            System.out.println("No presets found.");
        }
        presets.viewPresets();
    }
}

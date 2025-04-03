package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddNewDay extends MealPlanCommand {

    WeeklySchedule assignedWeeklySchedule;

    public AddNewDay() {
        super("addNewDay <week index>","Add New Day To Week");
    }

    public AddNewDay(WeeklySchedule week) {
        this.assignedWeeklySchedule = week;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        
    }
}

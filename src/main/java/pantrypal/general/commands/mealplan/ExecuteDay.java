package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class ExecuteDay extends MealPlanCommand {
    private int dayIndex;

    public ExecuteDay() {
        super("executeDay <dayIndex>",
                "Execute a meal plan");
    }

    public ExecuteDay(int dayIndex) {
        this.dayIndex = dayIndex;
    }


    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, WeeklySchedule week, Scanner in) {
        if (validateWeeklySchedule(week, false)) {
            week.executeDay(inventory, dayIndex);
            System.out.println("You have marked the day " + dayIndex + " as executed.");
        } else {
            System.out.println("There are no days in the week");
        }

    }
}

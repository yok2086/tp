package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddNewWeek extends MealPlanCommand {

    WeeklySchedule week;

    public AddNewWeek() {
        super("AddNewWeek",
                "Create weekly container for daily executable meal plans");
    }

    public AddNewWeek(PlanPresets preset) {
        week = new WeeklySchedule(preset);
    }

    public WeeklySchedule getWeeklySchedule() {
        return week;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {

    }
}

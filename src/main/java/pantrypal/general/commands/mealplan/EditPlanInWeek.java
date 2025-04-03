package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class EditPlanInWeek extends MealPlanCommand {
    private int dayIndex;

    public EditPlanInWeek() {
        super("EditPlanInWeek < dayIndex >",
                "Modify details of a plan in a day of the week");
    }

    public EditPlanInWeek(int dayIndex) {
        this.dayIndex = dayIndex;
    }


    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, WeeklySchedule week, Scanner in) {

        week.editDay(dayIndex, in);

        System.out.println("You edited the day " + dayIndex + ".");

    }
}

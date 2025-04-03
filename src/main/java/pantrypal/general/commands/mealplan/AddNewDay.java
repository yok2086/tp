package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddNewDay extends MealPlanCommand {

    int planIndex;
    int dayIndex;

    public AddNewDay() {
        super("addNewDay <plan index> <day index>",
                "Add New Day To Week");
    }

    public AddNewDay(int planIndex, int dayIndex) {
        this.planIndex = planIndex;
        this.dayIndex = dayIndex;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes,WeeklySchedule week, Scanner in) {

        week.addDay(presets, planIndex, dayIndex);

        System.out.println("Day added");
    }
}

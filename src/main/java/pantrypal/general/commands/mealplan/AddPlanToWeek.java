package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.Day;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddPlanToWeek extends MealPlanCommand {

    int planIndex;
    Day day;

    public AddPlanToWeek() {
        super("addPlanToList <plan name>", "Add a new plan to the list");
    }

    public AddPlanToWeek(int planIndex, int dayIndex) {
        this.planIndex = planIndex;
        this.day = getDay(dayIndex);
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,RecipeManager recipes,
                        MealPlanManager plans, Scanner in){
        try {
            plans.addPlanToWeek(planIndex, day);
        } catch (NullPointerException e) {
            Ui.showMessage("Invalid day index provided. Please enter a valid day index corresponding 1 to 7.");
        }
    }
}

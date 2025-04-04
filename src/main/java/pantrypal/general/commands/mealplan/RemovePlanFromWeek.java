package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemovePlanFromWeek extends MealPlanCommand {

    int dayIndex;

    public RemovePlanFromWeek() {
        super("addPlanToList <plan name>", "Add a new plan to the list");
    }

    public RemovePlanFromWeek(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,RecipeManager recipes,
                        MealPlanManager plans, Scanner in){
        try {
            plans.removePlanFromWeek(getDay(dayIndex));
        } catch (NullPointerException e) {
            Ui.showMessage("Invalid day index provided. Please enter a valid day index corresponding 1 to 7.");
        }
    }
}

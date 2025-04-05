package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemovePlanFromWeek extends MealPlanCommand {

    private String day;

    public RemovePlanFromWeek() {
        super("addPlanToList <plan name>", "Add a new plan to the list");
    }

    public RemovePlanFromWeek(String day) {
        this.day = day;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,RecipeManager recipes,
                        MealPlanManager plans, Scanner in){
        try {
            plans.removePlanFromWeek(getDay(day));
        } catch (NullPointerException e) {
            Ui.showMessage("Invalid day provided. Please enter a valid day name");
        } catch (IllegalArgumentException e) {
            ui.showMessage("Invalid input given. Please refrain from unconventional datatypes");
        }
    }
}

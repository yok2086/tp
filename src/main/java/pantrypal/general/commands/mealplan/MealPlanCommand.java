package pantrypal.general.commands.mealplan;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.Day;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public abstract class MealPlanCommand extends Command {

    public MealPlanCommand(String commandInstruction, String commandDescription) {
        super(commandInstruction, commandDescription);
    }

    public MealPlanCommand() {}

    public Day getDay(int dayIndex){
        for (Day day : Day.values()){
            if (day.ordinal() == dayIndex){
                return day;
            }
        }
        return null;
    }

    @Override
    public abstract void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                                 MealPlanManager plans, Scanner in);

}

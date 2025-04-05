package pantrypal.general.commands.mealplan;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.Day;
import pantrypal.mealplan.MealType;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public abstract class MealPlanCommand extends Command {

    public MealPlanCommand(String commandInstruction, String commandDescription) {
        super(commandInstruction, commandDescription);
    }

    public MealPlanCommand() {}

    public Day getDay(String day){
        try {
            return Day.valueOf(day.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public MealType getMealType(String mealName){
        try {
            return MealType.valueOf(mealName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public abstract void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                                 MealPlanManager plans, Scanner in);

}

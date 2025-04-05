package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddPlanToList extends MealPlanCommand {

    String planName;

    public AddPlanToList() {
        super("addPlanToList <plan name>", "Add a new plan to the list");
    }

    public AddPlanToList(String planName) {
        this.planName = planName;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,RecipeManager recipes,
                        MealPlanManager plans, Scanner in){
        plans.addPlanToList(planName);
    }
}

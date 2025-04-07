package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddPlan extends MealPlanCommand {

    private String planName;

    public AddPlan() {
        super("addPlan <plan name>", "Add a new plan");
    }

    public AddPlan(String planName) {
        this.planName = planName;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,RecipeManager recipes,
                        MealPlanManager plans, Scanner in){
        plans.addPlan(planName);
    }
}

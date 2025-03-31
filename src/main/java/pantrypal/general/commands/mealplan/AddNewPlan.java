package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddNewPlan extends MealPlanCommand {
    String planName;

    public AddNewPlan() {
        super("addPlan <name>","Add New Plan");
    }

    public AddNewPlan(String name) {
        this.planName = name;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        presets.addNewPlan(planName);
        System.out.println("Plan added");
    }
}

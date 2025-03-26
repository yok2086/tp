package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddNewPlan extends MealPlanCommand {
    private int duration;

    public AddNewPlan() {
        super("addPlan <duration>","Add New Plan");
    }

    public AddNewPlan(int duration) {
        this.duration = duration;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        presets.addNewPlan();
        System.out.println("Plan added");
    }
}

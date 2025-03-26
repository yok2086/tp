package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class RemovePlan extends MealPlanCommand {
    private int index;

    public RemovePlan() {
        super("removePlan <index>","Remove Meal Plan");
    }

    public RemovePlan(int duration) {
        this.index = duration;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        presets.removePlan(index);
    }
}

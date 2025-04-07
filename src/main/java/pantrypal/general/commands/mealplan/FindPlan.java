package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class FindPlan extends MealPlanCommand {

    String searchKey;

    public FindPlan() {
        super("findPlan <contains>", "Search amongst all created plans to find " +
                "one that contains the search key.");
    }

    public FindPlan(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchKey() {
        return searchKey;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {
        try {
            if(!plans.findInCreatedPlans(searchKey)){
                throw new NullPointerException("No plans matching the search key was found");
            }
        } catch (NullPointerException e) {
            Ui.showMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            Ui.showMessage("Invalid plan index provided. Please enter a valid plan index.");
        }
    }
}

package pantrypal.general.commands.inventory;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.inventory.Category;
import java.util.Scanner;

public class ViewIngredientsByCategory extends InventoryCommand {
    private Category category;

    public ViewIngredientsByCategory() {
        super("viewIngredientsByCategory <category>", "View ingredients by " +
                "their category");
    }

    public ViewIngredientsByCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {
        Ui.showMessage(inventory.viewIngredientsByCategory(category));
    }
}

package pantrypal.general.commands.general;
import java.util.Scanner;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

public class CategoryList extends GeneralCommand {

    public CategoryList() {
        super("categoryList", "List all valid categories");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        Ui.showMessage("All valid categories: " + "\n" +
                "DAIRY" + "\n" +
                "SPICES" + "\n" +
                "VEGETABLES" + "\n" +
                "FRUITS" + "\n" +
                "MEAT" + "\n" +
                "GRAINS" + "\n" +
                "CONDIMENTS" + "\n"
        );

    }
}

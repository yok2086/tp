package pantrypal.general.commands.general;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class UnitList extends GeneralCommand {

    public UnitList() {
        super("unitList", "List all valid units");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        Ui.showMessage("All valid units: " + "\n" +
                "GRAM(g)" + "\n" +
                "KILOGRAM(kg)" + "\n" +
                "TEASPOON(tsp)" + "\n" +
                "TABLESPOON(tbsp)" + "\n" +
                "CUP(cup)" + "\n" +
                "OUNCE(oz)" + "\n" +
                "LITER(L)" + "\n" +
                "MILLILITER(mL)" + "\n" +
                "POUND(lb)"
        );

    }
}
